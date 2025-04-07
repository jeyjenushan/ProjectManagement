import React from 'react'
import { useForm } from 'react-hook-form';
import { Button } from '@/components/ui/button'
import { Form, FormControl, FormField, FormItem, FormMessage } from '@/components/ui/form'
import { Input } from '@/components/ui/input'
import { Avatar, AvatarFallback } from '@/components/ui/avatar';
import { useDeferredValue } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { createComment } from '@/Redux/comment/Action';


const CreateCommentForm = ({issueId}) => {
    const dispatch=useDispatch()
    const {comment}=useSelector(store=>store)

      const form =useForm({
                defaultValues:{
                    content:"",
                }
            })
        
            const onSubmit=(data)=>{
                dispatch(createComment({content:data.content,issueId}))
                console.log("create project data",data);
            }
  return (
    <div>
        <Form {...form}>

<form className='flex gap-2' onSubmit={form.handleSubmit(onSubmit)}>
    <FormField
    control={form.control}
    name="content"
    render={({field})=><FormItem >
        <div className='flex gap-2'>
        <div>
            <Avatar>
                <AvatarFallback>R</AvatarFallback>
            </Avatar>
        </div>
        <FormControl>
               <Input
               {...field}
               type="text"
               className="w-[20rem] "
               placeholder="add a comment..."
               />
              </FormControl>

        </div>
    
          
              <FormMessage/>
    </FormItem>}
    />











<Button type="submit"  >Save</Button>



    

</form>


</Form>
      
    </div>
  )
}

export default CreateCommentForm
