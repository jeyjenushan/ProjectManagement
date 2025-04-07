import api from "@/config/api"
import { FETCH_CHAT_BY_PROJECT_FAILURE, FETCH_CHAT_BY_PROJECT_REQUEST, FETCH_CHAT_BY_PROJECT_SUCCESS, FETCH_CHAT_MESSAGES_FAILURE, FETCH_CHAT_MESSAGES_REQUEST, FETCH_CHAT_MESSAGES_SUCCESS, SEND_MESSAGE_REQUEST, SEND_MESSAGE_SUCCESS } from "./ActionTypes"
import { SEARCH_PROJECT_FAILURE } from "../Project/ActionTypes"



export const sendMessage=(messageData)=>{
    return async (dispatch)=>{
    dispatch({type:SEND_MESSAGE_REQUEST})
    try{
const response=await api.post("/api/messages/send",messageData)
console.log("message sent successfully",response.data)
dispatch({type:SEND_MESSAGE_SUCCESS,message:response.data})
}
catch(error){
console.log(error);
dispatch({type:SEARCH_PROJECT_FAILURE,message:error.message})
    }

}
}

export const fetchChatByProject=(projectId)=>{
    return async (dispatch)=>{
    dispatch({type:FETCH_CHAT_BY_PROJECT_REQUEST})
    try{
const {data}=await api.get(`/api/projects/${projectId}/chat `)
console.log("FETCH DATA",data)
dispatch({type:FETCH_CHAT_BY_PROJECT_SUCCESS,chat:data})
}
catch(error){
console.log(error);
dispatch({type:FETCH_CHAT_BY_PROJECT_FAILURE,chat:error.message})
    }

}
}

export const fetchChatMessages=(projectId)=>{
    return async (dispatch)=>{
    dispatch({type:FETCH_CHAT_MESSAGES_REQUEST})
    try{
const {data}=await api.get(`/api/messages/chat/${projectId} `)
console.log("FETCH DATA Messgaes",data)
dispatch({type:FETCH_CHAT_MESSAGES_SUCCESS,message:data})
}
catch(error){
console.log(error);
dispatch({type:FETCH_CHAT_MESSAGES_FAILURE,chat:error.message})
    }

}
}



