import React, { use, useEffect } from 'react'
import OrderCart from './OrderCart'
import { useDispatch, useSelector } from 'react-redux';
import { useNavigate } from 'react-router-dom';
import { getUsersOrders } from '../State/Order/Action';

const Orders = () => {
  const {auth , cart , order} = useSelector(store => store);
  const navigate = useNavigate();
  const dispatch = useDispatch();

  const jwt = localStorage.getItem("jwt");

  useEffect(() => { 
    dispatch(getUsersOrders(jwt))
  }, [auth.jwt])
  return (
    <div className='flex flex-col justify-center items-center '>
        <h1 className='text-2xl text-center font-semibold py-7'>My Orders</h1>
        <div className='space-y-5 w-full lg:w-1/2'>
            {
                order.orders.map((order) => order.items.map((item) => <OrderCart key={item.id} item={item} order={order} />))
            }
        </div>
    </div>
  )
}

export default Orders