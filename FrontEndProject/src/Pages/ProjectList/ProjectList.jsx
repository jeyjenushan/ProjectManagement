import { Button } from '@/components/ui/button'
import { Card, CardContent } from '@/components/ui/card'
import React, { useEffect, useState } from 'react'
import {MagnifyingGlassIcon, MixerHorizontalIcon} from '@radix-ui/react-icons'
import { ScrollArea } from "@/components/ui/scroll-area"

import { RadioGroup } from '@radix-ui/react-radio-group'
import { Label } from '@radix-ui/react-label'
import { RadioGroupItem } from '@/components/ui/radio-group'
import { Input } from '@/components/ui/input'
import ProjectCard from '../Project/ProjectCard'
import { useDispatch, useSelector } from 'react-redux'
import { fetchProjects, searchProjects } from '@/Redux/Project/Action'



export const tags=[
    "all","react","next.js","spring boot","mysql","mongodb","angular","python","flask","django"
]






function ProjectList() {
    const[keyword,setKeyword]=useState("")
    const {project}=useSelector(store=>store)
    const dispatch=useDispatch();


    const handleFilterCategory=(value)=>{
        if(value=="all"){
            dispatch(fetchProjects({}))
        }
        else{
            dispatch(fetchProjects({category:value}))
        }
      
       // console.log("value",value,section);
    }
    const handleFilterTags=(value)=>{
        if(value=="all"){
            dispatch(fetchProjects({}))
        }
        else{
            dispatch(fetchProjects({tag:value}))
            // console.log("value",value,section);
        }
  
    }

    const handleSearchChange=(e)=>{
setKeyword(e.target.value)
dispatch(searchProjects(e.target.value))
    }

     

  return (
    <div>
        <div className='relative px-5 lg:px-0 lg:flex gap-5 justify-center py-5'>
            <section className='filterSection'>
                <Card className="p-5 sticky top-10">
                    <div className='flex justify-between lg:w-[20rem]'>
                        <p className='text-xl -tracking-wider'>filters</p>
                    <Button variant="ghost" size="icon">
                        <MixerHorizontalIcon/>
                    </Button>
                    </div>
                    <CardContent className="mt-5">
                       <ScrollArea className='space-y-7 h-[70vh]' >
                     <div>
                        <h1 className='pb-3 text-gray-400 border-b'>
                            Category
                        </h1>
                        <div className='pt-5'>
                            <RadioGroup
                            defaultValue='all'
                            onValueChange={(value)=>handleFilterCategory(value)}
                             className='space-y-3 pt-5'
                            >
                                <div className='flex gap-2 items-center'>
                                    <RadioGroupItem value='all' id='r1'/>
                                     <Label htmlFor='r1'>all</Label>
                                </div>
                                <div className='flex gap-2 items-center'>
                                    <RadioGroupItem value='fullstack' id='r2'/>
                                     <Label htmlFor='r2'>fullstack</Label>
                                </div>
                                <div className='flex gap-2 items-center'>
                                    <RadioGroupItem value='frontend' id='r3'/>
                                     <Label htmlFor='r3'>frontend</Label>
                                </div>
                                <div className='flex gap-2 items-center'>
                                    <RadioGroupItem value='backend' id='r4'/>
                                     <Label htmlFor='r4'>backend</Label>
                                </div>
                            </RadioGroup>

                        </div>
                     </div>
                     <div className='pt-9'>
                        <h1 className='pb-3 text-gray-400 border-b'>
                            Tag
                        </h1>
                        <div className='pt-5'>
                            <RadioGroup
                            defaultValue='all'
                            onValueChange={(value)=>handleFilterTags(value)}
                            className='space-y-3 pt-5'
                            >
                             {tags.map((item)=>(  
                                <div className='flex gap-2 items-center'>
                                    <RadioGroupItem value={item} key={item} id={`r1-{item}`}/>
                                     <Label htmlFor={`r1-{item}`}>{item}</Label>
                                </div>
                                ))} 
                           
                            </RadioGroup>

                        </div>
                     </div>
                       </ScrollArea>


                    </CardContent>

                </Card>

            </section>


            <section className='projectListSection w-full lg:w-[48rem]'>
<div className='relative p-0 w-full mb-5'>
    <Input
    onChange={handleSearchChange}
    placeholder="search project"
    className="40% px-9"
    />
    <MagnifyingGlassIcon className='absolute top-3 left-4'/>
   

</div>
<div>
    <div className='space-y-5 min-h-[74vh]'>
        {
            keyword?
           project.searchProjects?.map((item)=><ProjectCard item={item} key={item.id+5}/>)
       
            :project.projects.map((item)=><ProjectCard key={item.id} item={item}/>)

        }

    </div>
</div>

            </section>
        </div>
      
    </div>
  )
}

export default ProjectList
