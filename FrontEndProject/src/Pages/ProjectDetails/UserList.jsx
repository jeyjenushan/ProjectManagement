import { Avatar, AvatarFallback } from '@/components/ui/avatar'
import { assignedUserToIssue } from '@/Redux/Issue/Action'
import React from 'react'
import { useDispatch, useSelector } from 'react-redux'

const UserList = ({issueDetails}) => {

    const {project}=useSelector(store=>store)
    const dispatch=useDispatch()
    const handleAssignIssueToUser=(userId)=>{
dispatch(assignedUserToIssue({issueId:issueDetails.id,userId:userId}))
    }
  return (
    <div className='space-y-2'>
        <div className='border rounded-md'>
            <p className='py-2 px-3'>
            {issueDetails?.assignee?.name || "unassignee"}
            </p>

        </div>
    
     {project.ProjectDetails?.team.map((item)=>(
        
        <div key={item.id} 
        onClick={()=>handleAssignIssueToUser(item.id)}
        className='py-2 group rounded-md border px-4 hover:bg-slate-800 cursor-pointer flex items-center space-x-4'>
      
            <Avatar key={item.id} >
                <AvatarFallback>
                  {item.name[0]}
                </AvatarFallback>
            </Avatar>
            <div className='space-y-1'>
                <p className='text-sm leading-none'>
                {item.name}
                </p>
                <p className='text-sm text-muted-foreground'>
                  @{item.name.toLowerCase()}
                </p>

            </div>
        </div>))}
      
    </div>
  )
}

export default UserList
