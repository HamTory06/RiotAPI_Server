package com.api.study.riot_api.domain.entity

import javax.persistence.*

@Entity
@Table(name  = "challenges")
class Challenges (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val idx: Long = 0L,
    val userName: String,
    val matchId: String,
    @Column(name = "12_assist_streak_count")
    val assist12StreakCount: Int,
    val abilityUses: Int,
    val acesBefore15Minutes: Int,
    val alliedJungleMonsterKills: Int,
    val baronTakedowns: Int,
    val blastConeOppositeOpponentCount: Int,
    val bountyGold: Int,
    val buffsStolen: Int,
    val completeSupportQuestInTime: Int,
    val controlWardTimeCoverageInRiverOrEnemyHalf: Double,
    val controlWardsPlaced: Int,
    val damagePerMinute: Double,
    val damageTakenOnTeamPercentage: Double,
    val dancedWithRiftHerald: Int,
    val deathsByEnemyChamps: Int,
    val dodgeSkillShotsSmallWindow: Int,
    val doubleAces: Int,
    val dragonTakedowns: Int,
    val earliestDragonTakedown: Double,
    val earlyLaningPhaseGoldExpAdvantage: Int,
    val effectiveHealAndShielding: Double,
    val elderDragonKillsWithOpposingSoul: Int,
    val elderDragonMultikills: Int,
    val enemyChampionImmobilizations: Int,
    val enemyJungleMonsterKills: Int,
    val epicMonsterKillsNearEnemyJungler: Int,
    val epicMonsterKillsWithin30SecondsOfSpawn: Int,
    val epicMonsterSteals: Int,
    val epicMonsterStolenWithoutSmite: Int,
    val firstTurretKilled: Int,
    val firstTurretKilledTime: Double,
    val flawlessAces: Int,
    val fullTeamTakedown: Int,
    val gameLength: Double,
    val getTakedownsInAllLanesEarlyJungleAsLaner: Int,
    val goldPerMinute: Double,
    val hadOpenNexus: Int,
    val highestChampionDamage: Int,
    val highestCrowdControlScore: Int,
    val highestWardKills: Int,
    val immobilizeAndKillWithAlly: Int,
    val initialBuffCount: Int,
    val initialCrabCount: Int,
    val jungleCsBefore10Minutes: Double,
    val junglerKillsEarlyJungle: Int,
    val junglerTakedownsNearDamagedEpicMonster: Int,
    val kda: Float,
    val killAfterHiddenWithAlly: Int,
    val killParticipation: Double,
    val killedChampTookFullTeamDamageSurvived: Int,
    val killingSprees: Int,
    val killsNearEnemyTurret: Int,
    val killsOnLanersEarlyJungleAsJungler: Int,
    val killsOnOtherLanesEarlyJungleAsLaner: Int,
    val killsOnRecentlyHealedByAramPack: Int,
    val killsUnderOwnTurret: Int,
    val killsWithHelpFromEpicMonster: Int,
    val knockEnemyIntoTeamAndKill: Int,
    val landSkillShotsEarlyGame: Int,
    val laneMinionsFirst10Minutes: Int,
    val laningPhaseGoldExpAdvantage: Int,
    val legendaryCount: Int,
    val lostAnInhibitor: Int,
    val maxCsAdvantageOnLaneOpponent: Double,
    val maxKillDeficit: Int,
    val maxLevelLeadLaneOpponent: Int,
    val mejaisFullStackInTime: Int,
    val moreEnemyJungleThanOpponent: Double,
    val multiKillOneSpell: Int,
    val multiTurretRiftHeraldCount: Int,
    val multikills: Int,
    val multikillsAfterAggressiveFlash: Int,
    val mythicItemUsed: Int,
    val outnumberedKills: Int,
    val outnumberedNexusKill: Int,
    val perfectDragonSoulsTaken: Int,
    val perfectGame: Int,
    val pickKillWithAlly: Int,
    val poroExplosions: Int,
    val quickCleanse: Int,
    val quickFirstTurret: Int,
    val quickSoloKills: Int,
    val riftHeraldTakedowns: Int,
    val saveAllyFromDeath: Int,
    val scuttleCrabKills: Int,
    val skillshotsDodged: Int,
    val skillshotsHit: Int,
    val snowballsHit: Int,
    val soloBaronKills: Int,
    val soloKills: Int,
    val soloTurretsLategame: Int,
    val stealthWardsPlaced: Int,
    val survivedSingleDigitHpCount: Int,
    val survivedThreeImmobilizesInFight: Int,
    val takedownOnFirstTurret: Int,
    val takedowns: Int,
    val takedownsAfterGainingLevelAdvantage: Int,
    val takedownsBeforeJungleMinionSpawn: Int,
    val takedownsFirstXMinutes: Int,
    val takedownsInAlcove: Int,
    val takedownsInEnemyFountain: Int,
    val teamBaronKills: Int,
    val teamDamagePercentage: Double,
    val teamElderDragonKills: Int,
    val teamRiftHeraldKills: Int,
    val teleportTakedowns: Int,
    val threeWardsOneSweeperCount: Int,
    val tookLargeDamageSurvived: Int,
    val turretPlatesTaken: Int,
    val turretTakedowns: Int,
    val turretsTakenWithRiftHerald: Int,
    val twentyMinionsIn3SecondsCount: Int,
    val unseenRecalls: Int,
    val visionScoreAdvantageLaneOpponent: Double,
    val visionScorePerMinute: Double,
    val wardTakedowns: Int,
    val wardTakedownsBefore20M: Int,
    val wardsGuarded: Int,
    val kturretsDestroyedBeforePlatesFall: Int,
)