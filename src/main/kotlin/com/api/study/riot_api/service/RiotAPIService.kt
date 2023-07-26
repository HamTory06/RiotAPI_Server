package com.api.study.riot_api.service

import com.api.study.riot_api.api.ExternalAsiaApiClient
import com.api.study.riot_api.api.ExternalDdragonApiClient
import com.api.study.riot_api.api.ExternalKrApiClient
import com.api.study.riot_api.api.ExternalKrDdragonLeagueoflegendsApiClient
import com.api.study.riot_api.domain.dto.riotapi.ddragon.champion.Data
import com.api.study.riot_api.domain.dto.riotapi.kr.ChampionMasteryDto
import com.api.study.riot_api.domain.dto.riotapi.kr.ChampionMasteryDtoArray
import com.api.study.riot_api.domain.entity.*
import com.api.study.riot_api.exception.CustomException
import com.api.study.riot_api.exception.ErrorCode
import com.api.study.riot_api.repository.*
import com.fasterxml.jackson.databind.ObjectMapper
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class RiotAPIService(
    private val lolRepository: LolRepository,
    private val matchRepository: MatchRepository,
    private val participantsRepository: ParticipantsRepository,
    private val participantsUserPuuIdRepository: ParticipantsUserPuuidRepository,
    private val challengesRepository: ChallengesRepository,
    private var externalKrApiClient: ExternalKrApiClient,
    private var externalAsiaApiClient: ExternalAsiaApiClient,
    private var externalDdragonApiClient: ExternalDdragonApiClient,
    private var championMasteryRepository: ChampionMasteryRepository,
    private var externalKrDdragonLeagueoflegendsApiClient: ExternalKrDdragonLeagueoflegendsApiClient
) {
    private val logger: Logger = LoggerFactory.getLogger(RiotAPIService::class.java)

    @Value("\${app.apiKey}")
    private val riotAPIKey = ""
    fun getLolUserInformation(userName: String): LolUser {
        return addLolUser(userName)

    }

    fun getMatchId(start: Int, count: Int, userName: String): List<String> {
        val puuId: String? = if (lolRepository.findByLolUserName(userName).isPresent) {
            val lolUserData = lolRepository.findByLolUserName(userName).get()
            lolUserData.lolUserPuuId
        } else {
            val riotLolUserData = getLolUserInformation(
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
        if (!matchRepository.findById(matchId).isPresent) {
            val matchInformationData = externalAsiaApiClient.getUserMatches(riotAPIKey, matchId)
            val metadata = matchInformationData.metadata
            val info = matchInformationData.info
            val matchInformation = MatchInformation(
                matchId = metadata.matchId,
                dataVersion = metadata.dataVersion,
                gameName = info.gameName,
                gameCreation = info.gameCreation,
                gameDuration = info.gameDuration,
                gameEndTimestamp = info.gameEndTimestamp,
                gameStartTimestamp = info.gameStartTimestamp,
                gameMode = info.gameMode,
                gameType = info.gameType,
                gameVersion = info.gameVersion,
                mapId = info.mapId
            )
            val participantsUserPuuId = ParticipantsUserPuuid(
                matchId = metadata.matchId,
                puuid_0 = metadata.participants[0],
                puuid_1 = metadata.participants[1],
                puuid_2 = metadata.participants[2],
                puuid_3 = metadata.participants[3],
                puuid_4 = metadata.participants[4],
                puuid_5 = metadata.participants[5],
                puuid_6 = metadata.participants[6],
                puuid_7 = metadata.participants[7],
                puuid_8 = metadata.participants[8],
                puuid_9 = metadata.participants[9]
            )
            matchRepository.save(matchInformation)
            participantsUserPuuIdRepository.save(participantsUserPuuId)
            for (i in 0..9) {
                val participants = matchInformationData.info.participants[i]
                val challenges = participants.challenges
                addLolUser(participants.summonerName)
                val participantsData = Participants(
                    idx = 0L,
                    allInPings = participants.allInPings,
                    assistMePings = participants.assistMePings,
                    assists = participants.assists,
                    baitPings = participants.baitPings,
                    baronKills = participants.baronKills,
                    basicPings = participants.basicPings,
                    bountyLevel = participants.bountyLevel,
                    physicalDamageDealt = participants.physicalDamageDealt,
                    physicalDamageDealtToChampions = participants.physicalDamageDealtToChampions,
                    physicalDamageTaken = participants.physicalDamageTaken,
                    profileIcon = participants.profileIcon,
                    pushPings = participants.pushPings,
                    puuid = participants.puuid,
                    quadraKills = participants.quadraKills,
                    riotIdName = participants.riotIdName,
                    riotIdTagline = participants.riotIdTagline,
                    role = participants.role,
                    sightWardsBoughtInGame = participants.sightWardsBoughtInGame,
                    spell1Casts = participants.spell1Casts,
                    spell2Casts = participants.spell2Casts,
                    spell3Casts = participants.spell3Casts,
                    spell4Casts = participants.spell4Casts,
                    summoner1Casts = participants.summoner1Casts,
                    summoner1Id = participants.summoner1Id,
                    summoner2Casts = participants.summoner2Casts,
                    summoner2Id = participants.summoner2Id,
                    summonerId = participants.summonerId,
                    summonerLevel = participants.summonerLevel,
                    summonerName = participants.summonerName,
                    teamEarlySurrendered = participants.teamEarlySurrendered,
                    teamId = participants.teamId,
                    teamPosition = participants.teamPosition,
                    timeCCingOthers = participants.timeCCingOthers,
                    timePlayed = participants.timePlayed,
                    totalAllyJungleMinionsKilled = participants.totalAllyJungleMinionsKilled,
                    totalDamageDealt = participants.totalDamageDealt,
                    totalDamageDealtToChampions = participants.totalDamageDealtToChampions,
                    totalDamageShieldedOnTeammates = participants.totalDamageShieldedOnTeammates,
                    totalDamageTaken = participants.totalDamageTaken,
                    totalHeal = participants.totalHeal,
                    totalHealsOnTeammates = participants.totalHealsOnTeammates,
                    totalEnemyJungleMinionsKilled = participants.totalEnemyJungleMinionsKilled,
                    totalMinionsKilled = participants.totalMinionsKilled,
                    totalTimeCCDealt = participants.totalTimeCCDealt,
                    totalTimeSpentDead = participants.totalTimeSpentDead,
                    totalUnitsHealed = participants.totalUnitsHealed,
                    tripleKills = participants.tripleKills,
                    turretTakedowns = participants.turretTakedowns,
                    trueDamageDealt = participants.trueDamageDealt,
                    trueDamageDealtToChampions = participants.trueDamageDealtToChampions,
                    trueDamageTaken = participants.trueDamageTaken,
                    turretKills = participants.turretKills,
                    turretsLost = participants.turretsLost,
                    unrealKills = participants.unrealKills,
                    visionClearedPings = participants.visionClearedPings,
                    visionScore = participants.visionScore,
                    visionWardsBoughtInGame = participants.visionWardsBoughtInGame,
                    wardsKilled = participants.wardsKilled,
                    wardsPlaced = participants.wardsPlaced,
                    win = participants.win,
                    champExperience = participants.champExperience,
                    championId = participants.championId,
                    championName = participants.championName,
                    championTransform = participants.championTransform,
                    champLevel = participants.champLevel,
                    commandPings = participants.commandPings,
                    consumablesPurchased = participants.consumablesPurchased,
                    damageDealtToBuildings = participants.damageDealtToBuildings,
                    damageDealtToObjectives = participants.damageDealtToObjectives,
                    damageDealtToTurrets = participants.damageDealtToTurrets,
                    damageSelfMitigated = participants.damageSelfMitigated,
                    dangerPings = participants.dangerPings,
                    deaths = participants.deaths,
                    detectorWardsPlaced = participants.detectorWardsPlaced,
                    doubleKills = participants.doubleKills,
                    dragonKills = participants.dragonKills,
                    eligibleForProgression = participants.eligibleForProgression,
                    enemyMissingPings = participants.enemyMissingPings,
                    enemyVisionPings = participants.enemyVisionPings,
                    firstBloodAssist = participants.firstBloodAssist,
                    firstBloodKill = participants.firstBloodKill,
                    firstTowerAssist = participants.firstTowerAssist,
                    firstTowerKill = participants.firstTowerKill,
                    gameEndedInEarlySurrender = participants.gameEndedInEarlySurrender,
                    gameEndedInSurrender = participants.gameEndedInSurrender,
                    getBackPings = participants.getBackPings,
                    goldEarned = participants.goldEarned,
                    goldSpent = participants.goldSpent,
                    holdPings = participants.holdPings,
                    individualPosition = participants.individualPosition,
                    inhibitorKills = participants.inhibitorKills,
                    inhibitorsLost = participants.inhibitorsLost,
                    inhibitorTakedowns = participants.inhibitorTakedowns,
                    item0 = participants.item0,
                    item1 = participants.item1,
                    item2 = participants.item2,
                    item3 = participants.item3,
                    item4 = participants.item4,
                    item5 = participants.item5,
                    item6 = participants.item6,
                    itemsPurchased = participants.itemsPurchased,
                    killingSprees = participants.killingSprees,
                    kills = participants.kills,
                    lane = participants.lane,
                    largestCriticalStrike = participants.largestCriticalStrike,
                    largestKillingSpree = participants.largestKillingSpree,
                    largestMultiKill = participants.largestMultiKill,
                    longestTimeSpentLiving = participants.longestTimeSpentLiving,
                    magicDamageDealt = participants.magicDamageDealt,
                    magicDamageDealtToChampions = participants.magicDamageDealtToChampions,
                    magicDamageTaken = participants.magicDamageTaken,
                    neutralMinionsKilled = participants.neutralMinionsKilled,
                    nexusLost = participants.nexusLost,
                    needVisionPings = participants.needVisionPings,
                    nexusKills = participants.nexusKills,
                    nexusTakedowns = participants.nexusTakedowns,
                    objectivesStolen = participants.objectivesStolen,
                    objectivesStolenAssists = participants.objectivesStolenAssists,
                    onMyWayPings = participants.onMyWayPings,
                    participantId = participants.participantId,
                    pentaKills = participants.pentaKills
                )
                val challengesData = Challenges(
                    idx = 0L,
                    userName = participants.summonerName,
                    matchId = matchInformationData.metadata.matchId,
                    abilityUses = challenges.abilityUses,
                    acesBefore15Minutes = challenges.acesBefore15Minutes,
                    alliedJungleMonsterKills = challenges.alliedJungleMonsterKills,
                    assist12StreakCount = challenges.assist12StreakCount,
                    baronTakedowns = challenges.baronTakedowns,
                    bountyGold = challenges.bountyGold,
                    buffsStolen = challenges.buffsStolen,
                    blastConeOppositeOpponentCount = challenges.blastConeOppositeOpponentCount,
                    completeSupportQuestInTime = challenges.completeSupportQuestInTime,
                    controlWardsPlaced = challenges.controlWardsPlaced,
                    controlWardTimeCoverageInRiverOrEnemyHalf = challenges.controlWardTimeCoverageInRiverOrEnemyHalf,
                    damagePerMinute = challenges.damagePerMinute,
                    damageTakenOnTeamPercentage = challenges.damageTakenOnTeamPercentage,
                    dancedWithRiftHerald = challenges.dancedWithRiftHerald,
                    deathsByEnemyChamps = challenges.deathsByEnemyChamps,
                    doubleAces = challenges.doubleAces,
                    dragonTakedowns = challenges.dragonTakedowns,
                    dodgeSkillShotsSmallWindow = challenges.dodgeSkillShotsSmallWindow,
                    earliestDragonTakedown = challenges.earliestDragonTakedown,
                    earlyLaningPhaseGoldExpAdvantage = challenges.earlyLaningPhaseGoldExpAdvantage,
                    effectiveHealAndShielding = challenges.effectiveHealAndShielding,
                    elderDragonKillsWithOpposingSoul = challenges.elderDragonMultikills,
                    elderDragonMultikills = challenges.elderDragonMultikills,
                    enemyChampionImmobilizations = challenges.enemyChampionImmobilizations,
                    enemyJungleMonsterKills = challenges.enemyJungleMonsterKills,
                    epicMonsterKillsNearEnemyJungler = challenges.epicMonsterKillsNearEnemyJungler,
                    epicMonsterSteals = challenges.epicMonsterSteals,
                    epicMonsterKillsWithin30SecondsOfSpawn = challenges.epicMonsterKillsWithin30SecondsOfSpawn,
                    epicMonsterStolenWithoutSmite = challenges.epicMonsterStolenWithoutSmite,
                    firstTurretKilled = challenges.firstTurretKilled,
                    flawlessAces = challenges.flawlessAces,
                    firstTurretKilledTime = challenges.firstTurretKilledTime,
                    fullTeamTakedown = challenges.fullTeamTakedown,
                    gameLength = challenges.gameLength,
                    goldPerMinute = challenges.goldPerMinute,
                    getTakedownsInAllLanesEarlyJungleAsLaner = challenges.getTakedownsInAllLanesEarlyJungleAsLaner,
                    hadOpenNexus = challenges.hadOpenNexus,
                    highestChampionDamage = challenges.highestChampionDamage,
                    highestCrowdControlScore = challenges.highestCrowdControlScore,
                    highestWardKills = challenges.highestWardKills,
                    immobilizeAndKillWithAlly = challenges.immobilizeAndKillWithAlly,
                    initialBuffCount = challenges.initialBuffCount,
                    initialCrabCount = challenges.initialCrabCount,
                    junglerKillsEarlyJungle = challenges.junglerKillsEarlyJungle,
                    jungleCsBefore10Minutes = challenges.jungleCsBefore10Minutes,
                    junglerTakedownsNearDamagedEpicMonster = challenges.junglerTakedownsNearDamagedEpicMonster,
                    killingSprees = challenges.killingSprees,
                    kda = challenges.kda,
                    killAfterHiddenWithAlly = challenges.killAfterHiddenWithAlly,
                    killParticipation = challenges.killParticipation,
                    killsNearEnemyTurret = challenges.killsNearEnemyTurret,
                    killedChampTookFullTeamDamageSurvived = challenges.killedChampTookFullTeamDamageSurvived,
                    killsOnLanersEarlyJungleAsJungler = challenges.killsOnLanersEarlyJungleAsJungler,
                    killsOnOtherLanesEarlyJungleAsLaner = challenges.killsOnOtherLanesEarlyJungleAsLaner,
                    killsOnRecentlyHealedByAramPack = challenges.killsOnRecentlyHealedByAramPack,
                    killsUnderOwnTurret = challenges.killsUnderOwnTurret,
                    killsWithHelpFromEpicMonster = challenges.killsWithHelpFromEpicMonster,
                    knockEnemyIntoTeamAndKill = challenges.knockEnemyIntoTeamAndKill,
                    kturretsDestroyedBeforePlatesFall = challenges.kTurretsDestroyedBeforePlatesFall,
                    laneMinionsFirst10Minutes = challenges.laneMinionsFirst10Minutes,
                    legendaryCount = challenges.legendaryCount,
                    laningPhaseGoldExpAdvantage = challenges.laningPhaseGoldExpAdvantage,
                    lostAnInhibitor = challenges.lostAnInhibitor,
                    landSkillShotsEarlyGame = challenges.landSkillShotsEarlyGame,
                    outnumberedKills = challenges.outnumberedKills,
                    outnumberedNexusKill = challenges.outnumberedNexusKill,
                    maxCsAdvantageOnLaneOpponent = challenges.maxCsAdvantageOnLaneOpponent,
                    maxKillDeficit = challenges.maxKillDeficit,
                    maxLevelLeadLaneOpponent = challenges.maxLevelLeadLaneOpponent,
                    mejaisFullStackInTime = challenges.mejaisFullStackInTime,
                    multikills = challenges.multikills,
                    moreEnemyJungleThanOpponent = challenges.moreEnemyJungleThanOpponent,
                    multiKillOneSpell = challenges.multiKillOneSpell,
                    multikillsAfterAggressiveFlash = challenges.multikillsAfterAggressiveFlash,
                    multiTurretRiftHeraldCount = challenges.multiTurretRiftHeraldCount,
                    mythicItemUsed = challenges.mythicItemUsed,
                    perfectGame = challenges.perfectGame,
                    perfectDragonSoulsTaken = challenges.perfectDragonSoulsTaken,
                    pickKillWithAlly = challenges.pickKillWithAlly,
                    poroExplosions = challenges.poroExplosions,
                    saveAllyFromDeath = challenges.saveAllyFromDeath,
                    scuttleCrabKills = challenges.scuttleCrabKills,
                    skillshotsDodged = challenges.skillshotsDodged,
                    skillshotsHit = challenges.skillshotsHit,
                    snowballsHit = challenges.snowballsHit,
                    soloBaronKills = challenges.soloBaronKills,
                    soloKills = challenges.soloKills,
                    soloTurretsLategame = challenges.soloTurretsLategame,
                    stealthWardsPlaced = challenges.stealthWardsPlaced,
                    survivedSingleDigitHpCount = challenges.survivedSingleDigitHpCount,
                    survivedThreeImmobilizesInFight = challenges.survivedThreeImmobilizesInFight,
                    quickCleanse = challenges.quickCleanse,
                    quickFirstTurret = challenges.quickFirstTurret,
                    quickSoloKills = challenges.quickSoloKills,
                    riftHeraldTakedowns = challenges.riftHeraldTakedowns,
                    takedowns = challenges.takedowns,
                    takedownOnFirstTurret = challenges.takedownOnFirstTurret,
                    takedownsAfterGainingLevelAdvantage = challenges.takedownsAfterGainingLevelAdvantage,
                    takedownsBeforeJungleMinionSpawn = challenges.takedownsBeforeJungleMinionSpawn,
                    takedownsFirstXMinutes = challenges.takedownsFirstXMinutes,
                    takedownsInAlcove = challenges.takedownsInAlcove,
                    takedownsInEnemyFountain = challenges.takedownsInEnemyFountain,
                    teamBaronKills = challenges.teamBaronKills,
                    teamDamagePercentage = challenges.teamDamagePercentage,
                    teamElderDragonKills = challenges.teamElderDragonKills,
                    teamRiftHeraldKills = challenges.teamRiftHeraldKills,
                    teleportTakedowns = challenges.teleportTakedowns,
                    threeWardsOneSweeperCount = challenges.threeWardsOneSweeperCount,
                    tookLargeDamageSurvived = challenges.tookLargeDamageSurvived,
                    turretPlatesTaken = challenges.turretPlatesTaken,
                    turretTakedowns = challenges.turretTakedowns,
                    turretsTakenWithRiftHerald = challenges.turretsTakenWithRiftHerald,
                    twentyMinionsIn3SecondsCount = challenges.twentyMinionsIn3SecondsCount,
                    unseenRecalls = challenges.unseenRecalls,
                    visionScoreAdvantageLaneOpponent = challenges.visionScoreAdvantageLaneOpponent,
                    visionScorePerMinute = challenges.visionScorePerMinute,
                    wardsGuarded = challenges.wardsGuarded,
                    wardTakedowns = challenges.wardTakedowns,
                    wardTakedownsBefore20M = challenges.wardTakedownsBefore20M
                )
                challengesRepository.save(challengesData)
                participantsRepository.save(participantsData)
            }
            return matchInformation
        } else {
            return matchRepository.findById(matchId).get()
        }
    }

    private fun addLolUser(lolName: String): LolUser {
        if (lolRepository.findByLolUserName(lolName).isEmpty) {
            val riotLolUserData = externalKrApiClient.getUserInformationName(
                apiKey = riotAPIKey,
                username = lolName
            )
            val lolUserData = LolUser(
                lolUserName = riotLolUserData.name,
                lolUserLevel = riotLolUserData.summonerLevel,
                lolUserPuuId = riotLolUserData.puuid,
                lolUserAccountId = riotLolUserData.accountId,
                lolUserId = riotLolUserData.id,
                lolUserUpdateTime = LocalDateTime.now()
            )

            lolRepository.save(
                lolUserData
            )
        } else {
            val lolUserData = lolRepository.findByLolUserName(lolName).get()
            if (lolUserData.lolUserUpdateTime.plusMinutes(5) > LocalDateTime.now())
                return lolUserData


            val riotLolUserData = externalKrApiClient.getUserUpdateInformationName(
                apiKey = riotAPIKey,
                username = lolName
            )
            val lolUserUpdateData = LolUser(
                lolUserName = riotLolUserData.name,
                lolUserLevel = riotLolUserData.summonerLevel,
                lolUserPuuId = lolUserData.lolUserPuuId,
                lolUserAccountId = lolUserData.lolUserAccountId,
                lolUserId = lolUserData.lolUserId,
                lolUserUpdateTime = LocalDateTime.now()
            )
            return lolRepository.save(lolUserUpdateData)
        }

        return lolRepository.findByLolUserName(lolName).get()
    }

    fun getUserChampionMasteries(lolName: String): ChampionMasteryDtoArray {
        val lolUserData = addLolUser(lolName)
        val championMasteries =
            externalKrApiClient.getUserChampionMasteries(
                lolUserId = lolUserData.lolUserId,
                apiKey = riotAPIKey,
                count = 10
            )
        for (i in 0 until championMasteries.size) {
            val championMastery = ChampionMastery(
                championId = championMasteries[i].championId,
                puuid = championMasteries[i].puuid,
                championLevel = championMasteries[i].championLevel,
                championPoints = championMasteries[i].championPoints,
                lastPlayTime = championMasteries[i].lastPlayTime,
                championPointsSinceLastLevel = championMasteries[i].championPointsSinceLastLevel,
                championPointsUntilNextLevel = championMasteries[i].championPointsUntilNextLevel,
                chestGranted = championMasteries[i].chestGranted,
                tokensEarned = championMasteries[i].tokensEarned,
                summonerId = championMasteries[i].summonerId
            )
            if (!championMasteryRepository.findByChampionId(championMastery.championId!!).isPresent)
                championMasteryRepository.save(championMastery)
        }
        return championMasteries
    }

    fun getUserChampionMasteries(lolName: String, champion: String): ChampionMasteryDto {
        val lolUserData = addLolUser(lolName)
        val version = externalKrDdragonLeagueoflegendsApiClient.getVersions()[0]
        logger.info(version)
        val championDataMap =
            externalKrDdragonLeagueoflegendsApiClient.getChampionInformation(version, "$champion.json").data.values
        val champion = championDataMap.map { it }
        val championKey = champion[0].key
        logger.info(championKey)
        return externalKrApiClient.getUserChampionMasteries(
            apiKey = riotAPIKey,
            lolUserId = lolUserData.lolUserId,
            championId = championKey
        )
    }

    fun getVersions(): String {
        return externalKrDdragonLeagueoflegendsApiClient.getVersions()[0]
    }
}