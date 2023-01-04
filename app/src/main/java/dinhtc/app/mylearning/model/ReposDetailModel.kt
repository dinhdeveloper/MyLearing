package dinhtc.app.mylearning.model

data class ReposDetailModel(
    val id : Int,
    val nodeId : String,
    val name : String,
    val fullName : String,
    val private : String,
    val owner : String,
    val htmlUrl : String,
    val description : String,
    val fork : String,
    val url : String,
    val forksUrl : String,
    val keysUrl : String,
    val collaboratorsUrl : String,
    val teamsUrl : String,
    val hooksUrl : String,
    val eventsUrl : String,
)
