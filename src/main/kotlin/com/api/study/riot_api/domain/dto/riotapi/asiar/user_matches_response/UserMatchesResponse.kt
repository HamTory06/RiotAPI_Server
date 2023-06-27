import com.api.study.riot_api.domain.dto.riotapi.asiar.user_matches_response.Info
import com.api.study.riot_api.domain.dto.riotapi.asiar.user_matches_response.Metadata

data class UserMatchesResponse(
    val info: Info,
    val metadata: Metadata
)