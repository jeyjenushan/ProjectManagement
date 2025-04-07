import api from "@/config/api"

export const createPayment=async({planType,jwt})=>{
      
    try{

        const {data}=await api.post(`/api/payment/${planType}`,{ headers: {
            Authorization: `Bearer ${jwt}`
        }})
        if(data.payment_link_url){
            window.location.href=data.payment_link_url;
        }
        return data.payment_link_id
    }catch(error){
        console.log(error);
    }
}