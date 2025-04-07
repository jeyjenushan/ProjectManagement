import { Button } from '@/components/ui/button'
import { DialogClose } from '@/components/ui/dialog'
import { Form, FormControl, FormField, FormItem, FormMessage } from '@/components/ui/form'
import { Input } from '@/components/ui/input'
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from '@/components/ui/select'
import React from 'react'
import { useForm } from 'react-hook-form'
import { tags } from './ProjectList'
import { Cross1Icon } from '@radix-ui/react-icons'
import { useDispatch } from 'react-redux'
import { createProject } from '@/Redux/Project/Action'



function CreatingProjectForm() {
    const dispatch=useDispatch();

    const handleTagsChange=(newvalue)=>{
        const currentTags=form.getValues("tags");
        const updatedTags=currentTags.includes(newvalue)?
        currentTags.filter(tag=>tag!=newvalue):[...currentTags,newvalue];

        form.setValue("tags",updatedTags);

    }



    const form =useForm({
        defaultValues:{
            name:"",
            description:"",
            category:"",
            tags:["javascript","react"]
        }
    })

    const onSubmit=(data)=>{
        dispatch(createProject(data))
        console.log("create project data",data);
    }

  return (
    <div>
<Form {...form}>

<form className='space-y-5' onSubmit={form.handleSubmit(onSubmit)}>
    <FormField
    control={form.control}
    name="name"
    render={({field})=><FormItem>
              <FormControl>
               <Input
               {...field}
               type="text"
               className="border w-full border-gray-700 py-5 px-5 "
               placeholder="project name..."
               />
              </FormControl>
              <FormMessage/>
    </FormItem>}
    />

<FormField
    control={form.control}
    name="description"
    render={({field})=><FormItem>
              <FormControl>
               <Input
               {...field}
               type="text"
               className="border w-full border-gray-700 py-5 px-5 "
               placeholder="project description..."
               />
              </FormControl>
              <FormMessage/>
    </FormItem>}
    />

<FormField
    control={form.control}
    name="category"
    render={({field})=><FormItem>
              <FormControl>
               <Select
          defaultValue='fullstack'
          value={field.value}
          onValueChange={(value)=>{
            field.onChange(value);
          }}
            >
                <SelectTrigger className="w-full">
                    <SelectValue placeholder="Category"/>
                </SelectTrigger>
                <SelectContent>
                    <SelectItem value="fullstack">fullstack</SelectItem>
                    <SelectItem value="frontend">frontend</SelectItem>
                    <SelectItem value="backend">backend</SelectItem>
                </SelectContent>


            </Select>

              </FormControl>
              <FormMessage/>
    </FormItem>}
    />


<FormField
    control={form.control}
    name="tags"
    render={({field})=><FormItem>
              <FormControl>
               <Select
       
          onValueChange={(value)=>{
           handleTagsChange(value)
          }}
            >
                <SelectTrigger className="w-full">
                    <SelectValue placeholder="Tags"/>
                </SelectTrigger>
                <SelectContent>

                    {tags.map((item)=>  <SelectItem key={item} value={item} >{item}</SelectItem>)}
                  
                
                </SelectContent>


            </Select>

              </FormControl>
       <div className='flex gap-1 flex-wrap'>
    {field.value.map((item)=>    <div key={item} onClick={()=>handleTagsChange(item)} className='cursor-pointer flex rounded-full px-4 items-center border gap-2 py-1'>
            <span className='text-sm'>{item}</span>
            <Cross1Icon className='h-3 w-3'/>
        </div>)}

       </div>


              <FormMessage/>
    </FormItem>}
    />




    <DialogClose>

{false?
<div>
    <p>you can create only 3 projects with free plan,please upgrade your plan</p>
</div>:<Button type="submit" className="w-full mt-5">Create Project</Button>
}

    </DialogClose>
    

</form>


</Form>
    </div>
  )
}

export default CreatingProjectForm
