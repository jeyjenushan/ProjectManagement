import { Avatar, AvatarFallback } from '@/components/ui/avatar'
import { Button } from '@/components/ui/button'
import { Input } from '@/components/ui/input'
import { ScrollArea } from '@/components/ui/scroll-area'
import { fetchChatByProject, fetchChatMessages, sendMessage } from '@/Redux/Chat/Action'
import { PaperPlaneIcon } from '@radix-ui/react-icons'

import React, { useEffect, useState } from 'react'
import { useDispatch, useSelector } from 'react-redux'
import { useParams } from 'react-router-dom'

const ChatBox = () => {
  const [message,setMessage]=useState("");
  const {auth,chat}=useSelector(store=>store)
  const {id}=useParams()
  const dispatch=useDispatch()

  /*useEffect(()=>{
    dispatch(fetchChatByProject(id))
  },[])*/
  useEffect(()=>{
    dispatch(fetchChatMessages(id))
  },[])

  const handleSendMessage=()=>{

    dispatch(sendMessage({
      userId:auth.user?.id,
      projectId:id,
      content:message
    }))
    setMessage("")
    console.log("message",message)

  }
  const handleMessageChange=(e)=>{
setMessage(e.target.value)
  }
  return (
    <div className='sticky'>
      <div className='border rounded-lg'>
        <h1 className='border-b p-5'>Chat Box</h1>
        
      <ScrollArea className="h-[32rem] w-full p-5 flex flex-col gap-3">

{chat.messages?.map((item,index)=>(
(item.user.id!==auth.user.id) ?<div key={item} className='flex justify-start gap-2 mb-2'>
<Avatar>
  <AvatarFallback>
    {item.user.name[0]}
  </AvatarFallback>
  </Avatar>
  <div className='space-y-2 py-2 px-5 border rounded-e-xl rounded-ss-2xl '>
<p>{item.user.name}</p>
<p className='text-gray-300'>{item.message}</p>
  </div>
  </div>:(
  <div key={item} className='flex justify-end gap-2 mb-2'>

    <div className='space-y-2 py-2 px-5 border rounded-s-xl rounded-se-2xl '>
    <p>{item.user.name}</p>
    <p className='text-gray-300'>{item.message}</p>
    </div>
    <Avatar>
    <AvatarFallback>
    {item.user.name[0]}
    </AvatarFallback>
    </Avatar>
  </div>
  )








))}





      </ScrollArea>
      <div className='relative p-0'>
        <Input
        placeHolder="type message..."
        className="py-7 border-t outline-none border-b-0 border-x-0 rounded-none focus:outline-none focus:ring-0"
         value={message} onChange={handleMessageChange}/>
         <Button onClick={handleSendMessage} size="icon" variant="ghost" className="absolute right-2 top-3 rounded-full">
<PaperPlaneIcon/>

         </Button>

      </div>


      </div>

    </div>
  )
}

export default ChatBox
