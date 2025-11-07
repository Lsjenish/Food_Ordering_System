import {api} from '../../config/api'
import { CREATE_INGREDIENT_CATEGORY_FAILURE, CREATE_INGREDIENT_CATEGORY_REQUEST, CREATE_INGREDIENT_CATEGORY_SUCCESS, CREATE_INGREDIENT_FAILURE, CREATE_INGREDIENT_REQUEST, CREATE_INGREDIENT_SUCCESS, GET_INGREDIENT_CATEGORY_FAILURE, GET_INGREDIENT_CATEGORY_REQUEST, GET_INGREDIENT_CATEGORY_SUCCESS, GET_INGREDIENTS, UPDATE_STOCK } from './ActionType'

export const getIngredientsOfRestaurant = ({id , jwt}) => {
    return async (dispatch) => {
        try{
            const response = await api.get(`/api/admin/ingredients/restaurant/${id}` , {
                headers : {
                    Authorization : `Bearer ${jwt}`
                }
            });
            console.log("get all ingredients " , response.data)
            dispatch({type : GET_INGREDIENTS , payload : response.data})
        }
        catch(error){
            console.log("error - " , error)
        }
    }
}

export const creatIngredient = ({data , jwt}) => {
    return async (dispatch) => {
        try{
            dispatch({type : CREATE_INGREDIENT_REQUEST})
            const response = await api.post(`/api/admin/ingredients` ,data, {
                headers : {
                    Authorization : `Bearer ${jwt}`
                }
            });
            console.log("create ingredients " , response.data)
            dispatch({type : CREATE_INGREDIENT_SUCCESS , payload : response.data})
        }
        catch(error){
            console.log("error - " , error)
            dispatch({type : CREATE_INGREDIENT_FAILURE , payload : error})
        }
    }
}

export const creatIngredientCategory = ({data , jwt}) => {
    return async (dispatch) => {
        try{
            dispatch({type : CREATE_INGREDIENT_CATEGORY_REQUEST})
            const response = await api.post(`/api/admin/ingredients/category` ,data, {
                headers : {
                    Authorization : `Bearer ${jwt}`
                }
            });
            console.log("create ingredients category " , response.data)
            dispatch({type : CREATE_INGREDIENT_CATEGORY_SUCCESS , payload : response.data})
        }
        catch(error){
            console.log("error - " , error)
            dispatch({type : CREATE_INGREDIENT_CATEGORY_FAILURE , payload : error})
        }
    }
}

export const getIngredientCategory = ({id , jwt}) => {
    return async (dispatch) => {
        try{
            dispatch({type : GET_INGREDIENT_CATEGORY_REQUEST})
            const response = await api.get(`/api/admin/ingredients/restaurant/${id}/category` , {
                headers : {
                    Authorization : `Bearer ${jwt}`
                }
            });
            console.log("get ingredients category " , response.data)
            dispatch({type : GET_INGREDIENT_CATEGORY_SUCCESS , payload : response.data})
        }
        catch(error){
            console.log("error - " , error)
            dispatch({type : GET_INGREDIENT_CATEGORY_FAILURE , payload : error})
        }
    }
}

export const updateStockOfIngredient = ({id , jwt}) => {
    return async (dispatch) => {
        try{
            const response = await api.put(`/api/admin/ingredients/${id}` ,{}, {
                headers : {
                    Authorization : `Bearer ${jwt}`
                }
            });
            console.log("updae ingredients stock " , response.data)
            dispatch({type : UPDATE_STOCK , payload : response.data})
        }
        catch(error){
            console.log("error - " , error)
        }
    }
}