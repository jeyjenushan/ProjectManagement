import api from "@/config/api"
import { ASSIGNED_ISSUE_TO_USER_REQUEST, ASSIGNED_ISSUE_TO_USER_SUCCESS, CREATE_ISSUE_FAILURE, CREATE_ISSUE_REQUEST, CREATE_ISSUE_SUCCESS, DELETE_ISSUE_FAILURE, DELETE_ISSUE_REQUEST, DELETE_ISSUE_SUCCESS, FETCH_ISSUES_BY_ID_FAILURE, FETCH_ISSUES_BY_ID_REQUEST,FETCH_ISSUES_BY_ID_SUCCESS, FETCH_ISSUES_FAILURE, FETCH_ISSUES_REQUEST, FETCH_ISSUES_SUCCESS, UPDATE_ISSUE_STATUS_FAILURE, UPDATE_ISSUE_STATUS_REQUEST, UPDATE_ISSUE_STATUS_SUCCESS } from "./ActionTypes"

import { SEARCH_PROJECT_FAILURE } from "../Project/ActionTypes"
import { DELETE_COMMENT_SUCCESS } from "../comment/ActionTypes"


export const fetchIssues=(id)=>async (dispatch)=>{
    dispatch({type:FETCH_ISSUES_REQUEST})
    try{
const response=await api.get("/api/issues/project/"+id)
console.log("fetch issues",response.data)
dispatch({type:FETCH_ISSUES_SUCCESS,issues:response.data})
}
catch(error){
console.log(error);
dispatch({type:FETCH_ISSUES_FAILURE,error:error.message})
    }

}


export const assignedUserToIssue=({issueId,userId})=>{
   return async (dispatch)=>{
    dispatch({type:ASSIGNED_ISSUE_TO_USER_REQUEST})
    try{
const response=await api.put(`/api/issues/${issueId}/assignee/${userId}`)
console.log("assigned issue--",response.data)
dispatch({type:ASSIGNED_ISSUE_TO_USER_SUCCESS,issue:response.data})
}
catch(error){
console.log(error);
dispatch({type:SEARCH_PROJECT_FAILURE,error:error.message})
    }

}
}




export const fetchIssueById=(id)=>async (dispatch)=>{
    dispatch({type:FETCH_ISSUES_BY_ID_REQUEST})
    try{
const response=await api.get(`/api/issues/${id}`,)
console.log(" fetch issue by id ",response.data)
dispatch({type:FETCH_ISSUES_BY_ID_SUCCESS,issue:response.data})
}
catch(error){
console.log(error);
dispatch({type:FETCH_ISSUES_BY_ID_FAILURE,error:error.message})
}
    }

export const updateIssueStatus=({id,status})=>{
    return async(dispatch)=>{
        dispatch({type:UPDATE_ISSUE_STATUS_REQUEST});
        try{
            const response=await api.put(`/api/issues/${id}/status/${status}`);
            console.log("update issue status",response.data);
               dispatch({
                type: UPDATE_ISSUE_STATUS_SUCCESS,
              issues:response.data
            });
            
        }
        catch(error){
            console.log(error);
            dispatch({type:UPDATE_ISSUE_STATUS_FAILURE,error:error.message})
            }
    }
}

export const createIssue=(data)=>{
    return async(dispatch)=>{
        dispatch({type:CREATE_ISSUE_REQUEST});
        try{
            const response=await api.post(`/api/issues`,data);
            dispatch({type:CREATE_ISSUE_SUCCESS,issues:response.data});
            console.log("issue created successfully ",response.data);
            
        }
        catch(error){
            console.log(error);
            dispatch({type:CREATE_ISSUE_FAILURE,error:error.message})
            }
    }
}

export const deleteIssueById=({issueId})=>async (dispatch)=>{
    dispatch({type:DELETE_ISSUE_REQUEST})
    try{
const response=await api.delete(`/api/issues/${issueId}`,)
console.log(" delete issue by id ",response.data)
dispatch({type:DELETE_ISSUE_SUCCESS,issueId})
}
catch(error){
console.log(error);
dispatch({type:DELETE_ISSUE_FAILURE,project:error.message})
}
    }



