import { Route, Routes } from 'react-router-dom'
import './App.css'
import Home from './Pages/Home/Home'
import Navbar from './Pages/Navbar/Navbar'
import ProjectDetails from './Pages/ProjectDetails/ProjectDetails'
import IssueDetails from './Pages/IssueDetails/IssueDetails'
import Subscription from './Pages/Subscription/Subscription'
import Auth from './Pages/Auth/Auth'
import { useDispatch, useSelector } from 'react-redux'
import { useEffect } from 'react'
import { getUser } from './Redux/Auth/Action'
import { fetchProjects } from './Redux/Project/Action'
import UpgradeSuccess from './Pages/Subscription/UpgradeSuccess'

function App() {
  const dispatch=useDispatch();
  const {auth} =useSelector(store=>store)


  useEffect(()=>{
    dispatch(getUser())
    dispatch(fetchProjects({}))
  },[auth.jwt])
  console.log("Auth user is : "+auth.user)


  return (
    <>
    {auth.user?
    <div>
      <Navbar/>
      <Routes>
        <Route path="/" element={<Home/>}/>
        <Route path="/project/:id" element={<ProjectDetails/>}/>
        <Route path='/project/:projectId/issue/:issueId' element={<IssueDetails/>}/>
        <Route path="/upgrade_plan" element={<Subscription/>}/>
        <Route path="/upgrade/success" element={<UpgradeSuccess/>}/>
     
      </Routes>
    </div>:<Auth/>
}

    </>

  )
}

export default App
