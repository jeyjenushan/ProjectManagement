import { API_BASE_URL } from "@/config/api";
import { GET_USER_REQUEST, GET_USER_SUCCESS, LOGIN_REQUEST, LOGIN_SUCCESS, LOGOUT, REGISTER_REQUEST, REGISTER_SUCCESS } from "./ActionTypes"
import axios from "axios";


//dispatch is used to send actions to the redux store
export const register=userData=>async(dispatch)=>{
    //indicate the register process has been sarted
    dispatch({type:REGISTER_REQUEST})
    try{

        const {data}=await axios.post(`${API_BASE_URL}/api/signup`,userData)
        if(data.jwt){
            localStorage.setItem("jwt",data.jwt)
            dispatch({type:REGISTER_SUCCESS,payload:data})
        }
        console.log("JJJJ"+localStorage.getItem("jwt"))
        console.log("register success",data)

    }catch(error){
        console.log(error);
    }
}

export const login=userData=>async(dispatch)=>{
    dispatch({type:LOGIN_REQUEST})
    try{

        const {data}=await axios.post(`${API_BASE_URL}/api/signing`,userData)
     
        if(data.jwt){
            localStorage.setItem("jwt",data.jwt)
            dispatch({type:REGISTER_SUCCESS,payload:data})
        }
        
        console.log("login success",data)

    }catch(error){
        console.log(error);
    }
}

export const getUser=()=>async(dispatch)=>{
    dispatch({type:GET_USER_REQUEST})
    try{
        console.log("jwt",localStorage.getItem("jwt"))

        const {data}=await axios.get(`${API_BASE_URL}/api/users/profile`,{
            headers:{
                "Authorization":`Bearer ${localStorage.getItem("jwt")}`
            }
        })
  
      
            dispatch({type:GET_USER_SUCCESS,payload:data})
        
        console.log("user success",data)

    }catch(error){
        console.log(error);
    }
}

export const logout=()=>async(dispatch)=>{
    dispatch({type:LOGOUT})
    localStorage.clear();
}