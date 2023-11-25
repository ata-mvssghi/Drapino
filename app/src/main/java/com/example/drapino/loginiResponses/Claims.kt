package com.example.drapino.loginiResponses

import com.google.gson.annotations.SerializedName

data class Claims(
    @SerializedName("http://schemas.xmlsoap.org/ws/2005/05/identity/claims/nameidentifier")
    val http_schemas_xmlsoap_org_ws_2005_05_identity_claims_nameidentifier: String,
    @SerializedName("http://schemas.xmlsoap.org/ws/2005/05/identity/claims/name\": \"09142409338")
    val http_schemas_xmlsoap_org_ws_2005_05_identity_claims_name: String,
    @SerializedName("AspNet.Identity.SecurityStamp\": \"4f318bee-822c-4310-9b3b-ba476390d849")
    val AspNet_Identity_SecurityStamp: String
)