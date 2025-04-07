import api from "@/config/api"
import { GET_USER_SUBSCRIPTION_FAILURE, GET_USER_SUBSCRIPTION_REQUEST, GET_USER_SUBSCRIPTION_SUCCESS, UPGRADE_SUBSCRIPTION_REQUEST } from "./ActionTypes"


export const getUserSubscription=()=>async (dispatch)=>{
    dispatch({type:GET_USER_SUBSCRIPTION_REQUEST})
    try{
const response=await api.get("/api/subscriptions/user",{
    headers:{
        "Authorization":`Bearer ${localStorage.getItem("jwt")}`
    }
})
console.log("users subscription",response.data)
dispatch({type:GET_USER_SUBSCRIPTION_SUCCESS,payload:response.data})
}
catch(error){
console.log(error);
dispatch({type:GET_USER_SUBSCRIPTION_FAILURE,error:error.message})
    }

}

export const upgradeSubscription=({planType})=>async (dispatch)=>{
    dispatch({type:UPGRADE_SUBSCRIPTION_REQUEST})
    try{
const response=await api.patch("/api/subscriptions/upgrade", 
    null,{headers:{
    "Authorization":`Bearer ${localStorage.getItem("jwt")}`
},
    params:{
        planType:planType
    }}
)
console.log("upgraded subscription",response.data)
dispatch({type:GET_USER_SUBSCRIPTION_SUCCESS,payload:response.data})
}
catch(error){
console.log(error);
dispatch({type:GET_USER_SUBSCRIPTION_FAILURE,error:error.message})
    }

}

