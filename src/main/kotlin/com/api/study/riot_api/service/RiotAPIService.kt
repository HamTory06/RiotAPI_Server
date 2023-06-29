package com.api.study.riot_api.service

import com.api.study.riot_api.api.ExternalAsiaApiClient
import com.api.study.riot_api.domain.entity.*
import com.api.study.riot_api.repository.*
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class RiotAPIService(
    private val userInformationService: UserInformationService,
    private val lolRepository: LolRepository,
    private val valRepository: ValRepository,
    private val matchRepository: MatchRepository,
    private val participantsRepository: ParticipantsRepository,
    private val participantsUserPuuidRepository: ParticipantsUserPuuidRepository,
    private val challengesRepository: ChallengesRepository
) {
    private val logger: Logger = LoggerFactory.getLogger(RiotAPIService::class.java)

    @Autowired
    private lateinit var externalAsiaApiClient: ExternalAsiaApiClient

    @Value("\${app.apiKey}")
    private val riotAPIKey = ""
    fun getLolUserInformation(apiKey: String, userName: String): LolUser {

        if (!lolRepository.findByLolUserName(userName).isPresent) {
            userInformationService.addLolUser(userName)
        }

        return lolRepository.findByLolUserName(userName).get()
    }

    fun getValUserInformation(apiKey: String, userName: String){
        if(!valRepository.findByValUserName(userName).isPresent){
            userInformationService.addValUser(userName)
        }
    }

    fun getMatchId(start: Int, count: Int, userName: String): List<String> {
        val puuId: String? = if(lolRepository.findByLolUserName(userName).isPresent){
            val lolUserData = lolRepository.findByLolUserName(userName).get()
            lolUserData.lolUserPuuId
        } else {
            val riotLolUserData = getLolUserInformation(
                apiKey = riotAPIKey,
                userName = userName
            )
            riotLolUserData.lolUserPuuId
        }

        return externalAsiaApiClient.getMatchId(
            apiKey = riotAPIKey,
            start = start,
            count = count,
            puuId = puuId!!
        )
    }

    fun getMatchInformation(matchId: String): MatchInformation {
        if(!matchRepository.findById(matchId).isPresent) {
            val matchInformationData = externalAsiaApiClient.getUserMatches(riotAPIKey, matchId)
            val matchInformation = MatchInformation(
                matchId = matchInformationData.metadata.matchId,
                dataVersion = matchInformationData.metadata.dataVersion,
                gameName = matchInformationData.info.gameName,
                gameCreation = matchInformationData.info.gameCreation,
                gameDuration = matchInformationData.info.gameDuration,
                gameEndTimestamp = matchInformationData.info.gameEndTimestamp,
                gameStartTimestamp = matchInformationData.info.gameStartTimestamp,
                gameMode = matchInformationData.info.gameMode,
                gameType = matchInformationData.info.gameType,
                gameVersion = matchInformationData.info.gameVersion,
                mapId = matchInformationData.info.mapId
            )
            matchRepository.save(matchInformation)
            val participantsUserPuuId = ParticipantsUserPuuid(
                matchId = matchInformationData.metadata.matchId,
                puuid_0 = matchInformationData.metadata.participants[0],
                puuid_1 = matchInformationData.metadata.participants[1],
                puuid_2 = matchInformationData.metadata.participants[2],
                puuid_3 = matchInformationData.metadata.participants[3],
                puuid_4 = matchInformationData.metadata.participants[4],
                puuid_5 = matchInformationData.metadata.participants[5],
                puuid_6 = matchInformationData.metadata.participants[6],
                puuid_7 = matchInformationData.metadata.participants[7],
                puuid_8 = matchInformationData.metadata.participants[8],
                puuid_9 = matchInformationData.metadata.participants[9]
            )
            participantsUserPuuidRepository.save(participantsUserPuuId)
            for(i in 0..9){
                val participants = Participants(
                    idx = 0L,
                    allInPings = matchInformationData.info.participants[i].allInPings,
                    assistMePings =  matchInformationData.info.participants[i].assistMePings,
                    assists =  matchInformationData.info.participants[i].assists,
                    baitPings =  matchInformationData.info.participants[i].baitPings,
                    baronKills = matchInformationData.info.participants[i].baronKills,
                    basicPings = matchInformationData.info.participants[i].basicPings,
                    bountyLevel =  matchInformationData.info.participants[i].bountyLevel,
                    physicalDamageDealt =  matchInformationData.info.participants[i].physicalDamageDealt,
                    physicalDamageDealtToChampions = matchInformationData.info.participants[i].physicalDamageDealtToChampions,
                    physicalDamageTaken = matchInformationData.info.participants[i].physicalDamageTaken,
                    profileIcon =  matchInformationData.info.participants[i].profileIcon,
                    pushPings = matchInformationData.info.participants[i].pushPings,
                    puuid = matchInformationData.info.participants[i].puuid,
                    quadraKills = matchInformationData.info.participants[i].quadraKills,
                    riotIdName = matchInformationData.info.participants[i].riotIdName,
                    riotIdTagline = matchInformationData.info.participants[i].riotIdTagline,
                    role = matchInformationData.info.participants[i].role,
                    sightWardsBoughtInGame = matchInformationData.info.participants[i].sightWardsBoughtInGame,
                    spell1Casts = matchInformationData.info.participants[i].spell1Casts,
                    spell2Casts = matchInformationData.info.participants[i].spell2Casts,
                    spell3Casts = matchInformationData.info.participants[i].spell3Casts,
                    spell4Casts = matchInformationData.info.participants[i].spell4Casts,
                    summoner1Casts = matchInformationData.info.participants[i].summoner1Casts,
                    summoner1Id = matchInformationData.info.participants[i].summoner1Id,
                    summoner2Casts = matchInformationData.info.participants[i].summoner2Casts,
                    summoner2Id = matchInformationData.info.participants[i].summoner2Id,
                    summonerId = matchInformationData.info.participants[i].summonerId,
                    summonerLevel = matchInformationData.info.participants[i].summonerLevel,
                    summonerName = matchInformationData.info.participants[i].summonerName,
                    teamEarlySurrendered = matchInformationData.info.participants[i].teamEarlySurrendered,
                    teamId = matchInformationData.info.participants[i].teamId,
                    teamPosition = matchInformationData.info.participants[i].teamPosition,
                    timeCCingOthers = matchInformationData.info.participants[i].timeCCingOthers,
                    timePlayed = matchInformationData.info.participants[i].timePlayed,
                    totalAllyJungleMinionsKilled = matchInformationData.info.participants[i].totalAllyJungleMinionsKilled,
                    totalDamageDealt = matchInformationData.info.participants[i].totalDamageDealt,
                    totalDamageDealtToChampions = matchInformationData.info.participants[i].totalDamageDealtToChampions,
                    totalDamageShieldedOnTeammates = matchInformationData.info.participants[i].totalDamageShieldedOnTeammates,
                    totalDamageTaken = matchInformationData.info.participants[i].totalDamageTaken,
                    totalHeal = matchInformationData.info.participants[i].totalHeal,
                    totalHealsOnTeammates = matchInformationData.info.participants[i].totalHealsOnTeammates,
                    totalEnemyJungleMinionsKilled = matchInformationData.info.participants[i].totalEnemyJungleMinionsKilled,
                    totalMinionsKilled = matchInformationData.info.participants[i].totalMinionsKilled,
                    totalTimeCCDealt = matchInformationData.info.participants[i].totalTimeCCDealt,
                    totalTimeSpentDead = matchInformationData.info.participants[i].totalTimeSpentDead,
                    totalUnitsHealed = matchInformationData.info.participants[i].totalUnitsHealed,
                    tripleKills = matchInformationData.info.participants[i].tripleKills,
                    turretTakedowns = matchInformationData.info.participants[i].turretTakedowns,
                    trueDamageDealt = matchInformationData.info.participants[i].trueDamageDealt,
                    trueDamageDealtToChampions = matchInformationData.info.participants[i].trueDamageDealtToChampions,
                    trueDamageTaken = matchInformationData.info.participants[i].trueDamageTaken,
                    turretKills = matchInformationData.info.participants[i].turretKills,
                    turretsLost = matchInformationData.info.participants[i].turretsLost,
                    unrealKills = matchInformationData.info.participants[i].unrealKills,
                    visionClearedPings = matchInformationData.info.participants[i].visionClearedPings,
                    visionScore = matchInformationData.info.participants[i].visionScore,
                    visionWardsBoughtInGame = matchInformationData.info.participants[i].visionWardsBoughtInGame,
                    wardsKilled = matchInformationData.info.participants[i].wardsKilled,
                    wardsPlaced = matchInformationData.info.participants[i].wardsPlaced,
                    win = matchInformationData.info.participants[i].win,
                    champExperience = matchInformationData.info.participants[i].champExperience,
                    championId = matchInformationData.info.participants[i].championId,
                    championName = matchInformationData.info.participants[i].championName,
                    championTransform = matchInformationData.info.participants[i].championTransform,
                    champLevel = matchInformationData.info.participants[i].champLevel,
                    commandPings = matchInformationData.info.participants[i].commandPings,
                    consumablesPurchased = matchInformationData.info.participants[i].consumablesPurchased,
                    damageDealtToBuildings = matchInformationData.info.participants[i].damageDealtToBuildings,
                    damageDealtToObjectives = matchInformationData.info.participants[i].damageDealtToObjectives,
                    damageDealtToTurrets = matchInformationData.info.participants[i].damageDealtToTurrets,
                    damageSelfMitigated = matchInformationData.info.participants[i].damageSelfMitigated,
                    dangerPings = matchInformationData.info.participants[i].dangerPings,
                    deaths = matchInformationData.info.participants[i].deaths,
                    detectorWardsPlaced = matchInformationData.info.participants[i].detectorWardsPlaced,
                    doubleKills = matchInformationData.info.participants[i].doubleKills,
                    dragonKills = matchInformationData.info.participants[i].dragonKills,
                    eligibleForProgression = matchInformationData.info.participants[i].eligibleForProgression,
                    enemyMissingPings = matchInformationData.info.participants[i].enemyMissingPings,
                    enemyVisionPings = matchInformationData.info.participants[i].enemyVisionPings,
                    firstBloodAssist = matchInformationData.info.participants[i].firstBloodAssist,
                    firstBloodKill = matchInformationData.info.participants[i].firstBloodKill,
                    firstTowerAssist = matchInformationData.info.participants[i].firstTowerAssist,
                    firstTowerKill = matchInformationData.info.participants[i].firstTowerKill,
                    gameEndedInEarlySurrender = matchInformationData.info.participants[i].gameEndedInEarlySurrender,
                    gameEndedInSurrender = matchInformationData.info.participants[i].gameEndedInSurrender,
                    getBackPings = matchInformationData.info.participants[i].getBackPings,
                    goldEarned = matchInformationData.info.participants[i].goldEarned,
                    goldSpent = matchInformationData.info.participants[i].goldSpent,
                    holdPings = matchInformationData.info.participants[i].holdPings,
                    individualPosition = matchInformationData.info.participants[i].individualPosition,
                    inhibitorKills = matchInformationData.info.participants[i].inhibitorKills,
                    inhibitorsLost = matchInformationData.info.participants[i].inhibitorsLost,
                    inhibitorTakedowns = matchInformationData.info.participants[i].inhibitorTakedowns,
                    item0 = matchInformationData.info.participants[i].item0,
                    item1 = matchInformationData.info.participants[i].item1,
                    item2 = matchInformationData.info.participants[i].item2,
                    item3 = matchInformationData.info.participants[i].item3,
                    item4 = matchInformationData.info.participants[i].item4,
                    item5 = matchInformationData.info.participants[i].item5,
                    item6 = matchInformationData.info.participants[i].item6,
                    itemsPurchased = matchInformationData.info.participants[i].itemsPurchased,
                    killingSprees = matchInformationData.info.participants[i].killingSprees,
                    kills = matchInformationData.info.participants[i].kills,
                    lane = matchInformationData.info.participants[i].lane,
                    largestCriticalStrike = matchInformationData.info.participants[i].largestCriticalStrike,
                    largestKillingSpree = matchInformationData.info.participants[i].largestKillingSpree,
                    largestMultiKill = matchInformationData.info.participants[i].largestMultiKill,
                    longestTimeSpentLiving = matchInformationData.info.participants[i].longestTimeSpentLiving,
                    magicDamageDealt = matchInformationData.info.participants[i].magicDamageDealt,
                    magicDamageDealtToChampions = matchInformationData.info.participants[i].magicDamageDealtToChampions,
                    magicDamageTaken = matchInformationData.info.participants[i].magicDamageTaken,
                    neutralMinionsKilled = matchInformationData.info.participants[i].neutralMinionsKilled,
                    nexusLost = matchInformationData.info.participants[i].nexusLost,
                    needVisionPings = matchInformationData.info.participants[i].needVisionPings,
                    nexusKills = matchInformationData.info.participants[i].nexusKills,
                    nexusTakedowns = matchInformationData.info.participants[i].nexusTakedowns,
                    objectivesStolen = matchInformationData.info.participants[i].objectivesStolen,
                    objectivesStolenAssists = matchInformationData.info.participants[i].objectivesStolenAssists,
                    onMyWayPings = matchInformationData.info.participants[i].onMyWayPings,
                    participantId = matchInformationData.info.participants[i].participantId,
                    pentaKills = matchInformationData.info.participants[i].pentaKills
                )
                participantsRepository.save(participants)

                logger.info("acesBefore15Minutes " + matchInformationData.info.participants[i].challenges.acesBefore15Minutes.toString())
                val challenges = Challenges(
                    idx = 0L,
                    userName = matchInformationData.info.participants[i].summonerName,
                    matchId = matchInformationData.metadata.matchId,
                    abilityUses = matchInformationData.info.participants[i].challenges.abilityUses,
                    acesBefore15Minutes = matchInformationData.info.participants[i].challenges.acesBefore15Minutes,
                    alliedJungleMonsterKills = matchInformationData.info.participants[i].challenges.alliedJungleMonsterKills,
                    assist12StreakCount = matchInformationData.info.participants[i].challenges.assist12StreakCount,
                    baronTakedowns = matchInformationData.info.participants[i].challenges.baronTakedowns,
                    bountyGold = matchInformationData.info.participants[i].challenges.bountyGold,
                    buffsStolen = matchInformationData.info.participants[i].challenges.buffsStolen,
                    blastConeOppositeOpponentCount = matchInformationData.info.participants[i].challenges.blastConeOppositeOpponentCount,
                    completeSupportQuestInTime = matchInformationData.info.participants[i].challenges.completeSupportQuestInTime,
                    controlWardsPlaced = matchInformationData.info.participants[i].challenges.controlWardsPlaced,
                    controlWardTimeCoverageInRiverOrEnemyHalf = matchInformationData.info.participants[i].challenges.controlWardTimeCoverageInRiverOrEnemyHalf,
                    damagePerMinute = matchInformationData.info.participants[i].challenges.damagePerMinute,
                    damageTakenOnTeamPercentage = matchInformationData.info.participants[i].challenges.damageTakenOnTeamPercentage,
                    dancedWithRiftHerald = matchInformationData.info.participants[i].challenges.dancedWithRiftHerald,
                    deathsByEnemyChamps = matchInformationData.info.participants[i].challenges.deathsByEnemyChamps,
                    doubleAces = matchInformationData.info.participants[i].challenges.doubleAces,
                    dragonTakedowns = matchInformationData.info.participants[i].challenges.dragonTakedowns,
                    dodgeSkillShotsSmallWindow = matchInformationData.info.participants[i].challenges.dodgeSkillShotsSmallWindow,
                    earliestDragonTakedown = matchInformationData.info.participants[i].challenges.earliestDragonTakedown,
                    earlyLaningPhaseGoldExpAdvantage = matchInformationData.info.participants[i].challenges.earlyLaningPhaseGoldExpAdvantage,
                    effectiveHealAndShielding = matchInformationData.info.participants[i].challenges.effectiveHealAndShielding,
                    elderDragonKillsWithOpposingSoul = matchInformationData.info.participants[i].challenges.elderDragonMultikills,
                    elderDragonMultikills = matchInformationData.info.participants[i].challenges.elderDragonMultikills,
                    enemyChampionImmobilizations = matchInformationData.info.participants[i].challenges.enemyChampionImmobilizations,
                    enemyJungleMonsterKills = matchInformationData.info.participants[i].challenges.enemyJungleMonsterKills,
                    epicMonsterKillsNearEnemyJungler = matchInformationData.info.participants[i].challenges.epicMonsterKillsNearEnemyJungler,
                    epicMonsterSteals = matchInformationData.info.participants[i].challenges.epicMonsterSteals,
                    epicMonsterKillsWithin30SecondsOfSpawn = matchInformationData.info.participants[i].challenges.epicMonsterKillsWithin30SecondsOfSpawn,
                    epicMonsterStolenWithoutSmite = matchInformationData.info.participants[i].challenges.epicMonsterStolenWithoutSmite,
                    firstTurretKilled = matchInformationData.info.participants[i].challenges.firstTurretKilled,
                    flawlessAces = matchInformationData.info.participants[i].challenges.flawlessAces,
                    firstTurretKilledTime = matchInformationData.info.participants[i].challenges.firstTurretKilledTime,
                    fullTeamTakedown = matchInformationData.info.participants[i].challenges.fullTeamTakedown,
                    gameLength = matchInformationData.info.participants[i].challenges.gameLength,
                    goldPerMinute = matchInformationData.info.participants[i].challenges.goldPerMinute,
                    getTakedownsInAllLanesEarlyJungleAsLaner = matchInformationData.info.participants[i].challenges.getTakedownsInAllLanesEarlyJungleAsLaner,
                    hadOpenNexus = matchInformationData.info.participants[i].challenges.hadOpenNexus,
                    highestChampionDamage = matchInformationData.info.participants[i].challenges.highestChampionDamage,
                    highestCrowdControlScore = matchInformationData.info.participants[i].challenges.highestCrowdControlScore,
                    highestWardKills = matchInformationData.info.participants[i].challenges.highestWardKills,
                    immobilizeAndKillWithAlly = matchInformationData.info.participants[i].challenges.immobilizeAndKillWithAlly,
                    initialBuffCount = matchInformationData.info.participants[i].challenges.initialBuffCount,
                    initialCrabCount = matchInformationData.info.participants[i].challenges.initialCrabCount,
                    junglerKillsEarlyJungle = matchInformationData.info.participants[i].challenges.junglerKillsEarlyJungle,
                    jungleCsBefore10Minutes = matchInformationData.info.participants[i].challenges.jungleCsBefore10Minutes,
                    junglerTakedownsNearDamagedEpicMonster = matchInformationData.info.participants[i].challenges.junglerTakedownsNearDamagedEpicMonster,
                    killingSprees = matchInformationData.info.participants[i].challenges.killingSprees,
                    kda = matchInformationData.info.participants[i].challenges.kda,
                    killAfterHiddenWithAlly = matchInformationData.info.participants[i].challenges.killAfterHiddenWithAlly,
                    killParticipation = matchInformationData.info.participants[i].challenges.killParticipation,
                    killsNearEnemyTurret = matchInformationData.info.participants[i].challenges.killsNearEnemyTurret,
                    killedChampTookFullTeamDamageSurvived = matchInformationData.info.participants[i].challenges.killedChampTookFullTeamDamageSurvived,
                    killsOnLanersEarlyJungleAsJungler = matchInformationData.info.participants[i].challenges.killsOnLanersEarlyJungleAsJungler,
                    killsOnOtherLanesEarlyJungleAsLaner = matchInformationData.info.participants[i].challenges.killsOnOtherLanesEarlyJungleAsLaner,
                    killsOnRecentlyHealedByAramPack = matchInformationData.info.participants[i].challenges.killsOnRecentlyHealedByAramPack,
                    killsUnderOwnTurret = matchInformationData.info.participants[i].challenges.killsUnderOwnTurret,
                    killsWithHelpFromEpicMonster = matchInformationData.info.participants[i].challenges.killsWithHelpFromEpicMonster,
                    knockEnemyIntoTeamAndKill = matchInformationData.info.participants[i].challenges.knockEnemyIntoTeamAndKill,
                    kturretsDestroyedBeforePlatesFall = matchInformationData.info.participants[i].challenges.kTurretsDestroyedBeforePlatesFall,
                    laneMinionsFirst10Minutes = matchInformationData.info.participants[i].challenges.laneMinionsFirst10Minutes,
                    legendaryCount = matchInformationData.info.participants[i].challenges.legendaryCount,
                    laningPhaseGoldExpAdvantage = matchInformationData.info.participants[i].challenges.laningPhaseGoldExpAdvantage,
                    lostAnInhibitor = matchInformationData.info.participants[i].challenges.lostAnInhibitor,
                    landSkillShotsEarlyGame = matchInformationData.info.participants[i].challenges.landSkillShotsEarlyGame,
                    outnumberedKills = matchInformationData.info.participants[i].challenges.outnumberedKills,
                    outnumberedNexusKill = matchInformationData.info.participants[i].challenges.outnumberedNexusKill,
                    maxCsAdvantageOnLaneOpponent = matchInformationData.info.participants[i].challenges.maxCsAdvantageOnLaneOpponent,
                    maxKillDeficit = matchInformationData.info.participants[i].challenges.maxKillDeficit,
                    maxLevelLeadLaneOpponent = matchInformationData.info.participants[i].challenges.maxLevelLeadLaneOpponent,
                    mejaisFullStackInTime = matchInformationData.info.participants[i].challenges.mejaisFullStackInTime,
                    multikills = matchInformationData.info.participants[i].challenges.multikills,
                    moreEnemyJungleThanOpponent = matchInformationData.info.participants[i].challenges.moreEnemyJungleThanOpponent,
                    multiKillOneSpell = matchInformationData.info.participants[i].challenges.multiKillOneSpell,
                    multikillsAfterAggressiveFlash = matchInformationData.info.participants[i].challenges.multikillsAfterAggressiveFlash,
                    multiTurretRiftHeraldCount = matchInformationData.info.participants[i].challenges.multiTurretRiftHeraldCount,
                    mythicItemUsed = matchInformationData.info.participants[i].challenges.mythicItemUsed,
                    perfectGame = matchInformationData.info.participants[i].challenges.perfectGame,
                    perfectDragonSoulsTaken = matchInformationData.info.participants[i].challenges.perfectDragonSoulsTaken,
                    pickKillWithAlly = matchInformationData.info.participants[i].challenges.pickKillWithAlly,
                    poroExplosions = matchInformationData.info.participants[i].challenges.poroExplosions,
                    saveAllyFromDeath = matchInformationData.info.participants[i].challenges.saveAllyFromDeath,
                    scuttleCrabKills = matchInformationData.info.participants[i].challenges.scuttleCrabKills,
                    skillshotsDodged = matchInformationData.info.participants[i].challenges.skillshotsDodged,
                    skillshotsHit = matchInformationData.info.participants[i].challenges.skillshotsHit,
                    snowballsHit = matchInformationData.info.participants[i].challenges.snowballsHit,
                    soloBaronKills = matchInformationData.info.participants[i].challenges.soloBaronKills,
                    soloKills = matchInformationData.info.participants[i].challenges.soloKills,
                    soloTurretsLategame = matchInformationData.info.participants[i].challenges.soloTurretsLategame,
                    stealthWardsPlaced = matchInformationData.info.participants[i].challenges.stealthWardsPlaced,
                    survivedSingleDigitHpCount = matchInformationData.info.participants[i].challenges.survivedSingleDigitHpCount,
                    survivedThreeImmobilizesInFight = matchInformationData.info.participants[i].challenges.survivedThreeImmobilizesInFight,
                    quickCleanse = matchInformationData.info.participants[i].challenges.quickCleanse,
                    quickFirstTurret = matchInformationData.info.participants[i].challenges.quickFirstTurret,
                    quickSoloKills = matchInformationData.info.participants[i].challenges.quickSoloKills,
                    riftHeraldTakedowns = matchInformationData.info.participants[i].challenges.riftHeraldTakedowns,
                    takedowns = matchInformationData.info.participants[i].challenges.takedowns,
                    takedownOnFirstTurret = matchInformationData.info.participants[i].challenges.takedownOnFirstTurret,
                    takedownsAfterGainingLevelAdvantage = matchInformationData.info.participants[i].challenges.takedownsAfterGainingLevelAdvantage,
                    takedownsBeforeJungleMinionSpawn = matchInformationData.info.participants[i].challenges.takedownsBeforeJungleMinionSpawn,
                    takedownsFirstXMinutes = matchInformationData.info.participants[i].challenges.takedownsFirstXMinutes,
                    takedownsInAlcove = matchInformationData.info.participants[i].challenges.takedownsInAlcove,
                    takedownsInEnemyFountain = matchInformationData.info.participants[i].challenges.takedownsInEnemyFountain,
                    teamBaronKills = matchInformationData.info.participants[i].challenges.teamBaronKills,
                    teamDamagePercentage = matchInformationData.info.participants[i].challenges.teamDamagePercentage,
                    teamElderDragonKills = matchInformationData.info.participants[i].challenges.teamElderDragonKills,
                    teamRiftHeraldKills = matchInformationData.info.participants[i].challenges.teamRiftHeraldKills,
                    teleportTakedowns = matchInformationData.info.participants[i].challenges.teleportTakedowns,
                    threeWardsOneSweeperCount = matchInformationData.info.participants[i].challenges.threeWardsOneSweeperCount,
                    tookLargeDamageSurvived = matchInformationData.info.participants[i].challenges.tookLargeDamageSurvived,
                    turretPlatesTaken = matchInformationData.info.participants[i].challenges.turretPlatesTaken,
                    turretTakedowns = matchInformationData.info.participants[i].challenges.turretTakedowns,
                    turretsTakenWithRiftHerald = matchInformationData.info.participants[i].challenges.turretsTakenWithRiftHerald,
                    twentyMinionsIn3SecondsCount = matchInformationData.info.participants[i].challenges.twentyMinionsIn3SecondsCount,
                    unseenRecalls = matchInformationData.info.participants[i].challenges.unseenRecalls,
                    visionScoreAdvantageLaneOpponent = matchInformationData.info.participants[i].challenges.visionScoreAdvantageLaneOpponent,
                    visionScorePerMinute = matchInformationData.info.participants[i].challenges.visionScorePerMinute,
                    wardsGuarded = matchInformationData.info.participants[i].challenges.wardsGuarded,
                    wardTakedowns = matchInformationData.info.participants[i].challenges.wardTakedowns,
                    wardTakedownsBefore20M = matchInformationData.info.participants[i].challenges.wardTakedownsBefore20M
                )
                challengesRepository.save(challenges)
            }

            return matchInformation
        } else {
            return matchRepository.findById(matchId).get()
        }
    }
}