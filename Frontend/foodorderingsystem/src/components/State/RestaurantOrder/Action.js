import axios from "axios";
import {api} from '../../config/api'
import { GET_RESTAURANTS_ORDER_FAILURE, GET_RESTAURANTS_ORDER_REQUEST, GET_RESTAURANTS_ORDER_SUCCESS, UPDATE_ORDER_STATUS_FAILURE, UPDATE_ORDER_STATUS_REQUEST, UPDATE_ORDER_STATUS_SUCCESS } from "./ActionType";

export const updateOrderStatus = ({orderId , orderStatus , jwt}) => {
    return async (dispatch) => {
        try{
        dispatch({type : UPDATE_ORDER_STATUS_REQUEST})
            const response = await api.put(`/api/admin/orders/${orderId}/${orderStatus}` , {} , {
                headers : {
                    Authorization : `Bearer ${jwt}`
                }
            })
            console.log("updated order - "  , response.data)
            dispatch({type : UPDATE_ORDER_STATUS_SUCCESS , payload :response.data})
        }
        catch(error){
            console.log("error - " , error)
            dispatch({type : UPDATE_ORDER_STATUS_FAILURE , payload : error})
        }
    }
}

export const fetchRestaurantsOrder = ({restaurantId , orderStatus , jwt}) => {
    return async (dispatch) => {
        try{
        dispatch({type : GET_RESTAURANTS_ORDER_REQUEST})
            const response = await api.get(`/api/admin/order/restaurant/${restaurantId}` , {
                params: {order_status : orderStatus},
                headers : {
                    Authorization : `Bearer ${jwt}`
                }
            })
            console.log("restaurants order ---- "  , response.data)
            dispatch({type : GET_RESTAURANTS_ORDER_SUCCESS , payload :response.data})
        }
        catch(error){
            console.log("error - " , error)
            dispatch({type : GET_RESTAURANTS_ORDER_FAILURE , payload : error})
        }
    }
}