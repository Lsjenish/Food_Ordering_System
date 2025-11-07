import React from 'react'
import RestaurantCart from '../Restaurant/RestaurantCart'
import { useSelector } from 'react-redux'

const Favorites = () => {
  const {auth} = useSelector(store => store)

  return (
    <div className='mt-16'>
        <h1 className='text-xl text-center font-semibold py-5'>
            My Favorites
        </h1>
        <div className='flex flex-wrap gap-1 justify-center'>
            {auth.favorites.map((item) => <RestaurantCart item={item} />)}
        </div>
    </div>
  )
}

export default Favorites