import { applyMiddleware, combineReducers, legacy_createStore } from "redux";
import { thunk } from "redux-thunk";
import {authReducer} from "./Auth/Reducer";
import { ProjectReducer } from "./Project/Reducer";
import { commentReducer } from "./comment/Reducer";
import { IssueReducer } from "./Issue/Reducer";
import { chatReducer } from "./Chat/Reducer";
import { subscriptionReducer } from "./Subscription/Reducer";
//npm i redux react-redux redux-thunk

const rootReducer=combineReducers({
    auth:authReducer,
    project:ProjectReducer,
    chat:chatReducer,
    comment:commentReducer,
    issue:IssueReducer,
    subscription:subscriptionReducer

    

})

export const store=legacy_createStore(rootReducer,applyMiddleware(thunk))