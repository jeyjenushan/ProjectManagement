import ProjectDetails from "@/Pages/ProjectDetails/ProjectDetails"
import {  ACCEPT_INVITATION_REQUEST, ACCEPT_INVITATION_SUCCESS, CREATE_PROJECT_FAILURE, CREATE_PROJECT_REQUEST, CREATE_PROJECT_SUCCESS, DELETE_PROJECT_FAILURE, DELETE_PROJECT_REQUEST, DELETE_PROJECT_SUCCESS, FETCH_PROJECT_BY_ID_FAILURE, FETCH_PROJECT_BY_ID_REQUEST, FETCH_PROJECT_BY_ID_SUCCESS, FETCH_PROJECTS_REQUEST, FETCH_PROJECTS_SUCCESS, INVITE_TO_PROJECT_REQUEST, INVITE_TO_PROJECT_SUCCESS, SEARCH_PROJECT_FAILURE, SEARCH_PROJECT_REQUEST, SEARCH_PROJECT_SUCCESS } from "./ActionTypes"



const initialState={
    projects:[],
    loading:false,
    error:null,
    ProjectDetails:null,
    searchProjects:[]
}
export const ProjectReducer=(state=initialState,action)=>{
    switch(action.type){

        case FETCH_PROJECTS_REQUEST:
        case CREATE_PROJECT_REQUEST:
        case ACCEPT_INVITATION_REQUEST:
        case DELETE_PROJECT_REQUEST:
        case INVITE_TO_PROJECT_REQUEST:
        case SEARCH_PROJECT_REQUEST:
        case FETCH_PROJECT_BY_ID_REQUEST:
            return{...state,loading:true,error:null}

        case FETCH_PROJECTS_SUCCESS:
            return{...state,loading:false,error:null,
                projects:action.projects}
        
        case SEARCH_PROJECT_SUCCESS:
            return{...state,loading:false,error:null,
                searchProjects:action.projects}
        
        case CREATE_PROJECT_SUCCESS:
            return{...state,loading:false,error:null,
                projects:[...state.projects,action.project]}
        
        case FETCH_PROJECT_BY_ID_SUCCESS:
            return{...state,loading:false,error:null,
                        ProjectDetails:action.project}
        
        case DELETE_PROJECT_SUCCESS:
            return{...state,loading:false,error:null,
                    projects:state.projects.filter(project=>project.id !==action.projectId)}
                
               
        case DELETE_PROJECT_FAILURE:
        case CREATE_PROJECT_FAILURE:
        case FETCH_PROJECT_BY_ID_FAILURE:
        case SEARCH_PROJECT_FAILURE:
            return{...state,loading:false,
                               error:action.error
                           }  
     
        default:
            return state;


    }

}

