import React, { use } from 'react'
import {Card, Chip, IconButton} from '@mui/material'
import FavoriteIcon from '@mui/icons-material/Favorite';
import FavoriteBorderIcon from '@mui/icons-material/FavoriteBorder';
import { useNavigate } from 'react-router-dom';
import { useDispatch, useSelector } from 'react-redux';
import { addToFavorite } from '../State/Authentication/Action';
import { isPresentInFavorites } from '../config/logic';

const RestaurantCart = ({item}) => {
    const  navigate = useNavigate()
    const dispatch = useDispatch()
    const auth = useSelector(store => store.auth)
    const jwt = localStorage.getItem("jwt")
    const handleAddToFavorite = () => {
        dispatch(addToFavorite(jwt ,item.id))
    }

    const handleNavigateToRestaurant = () => {
        if(item.open){
            navigate(`/restaurant/${item.address.city}/${item.name}/${item.id}`)
        }
    }

  return (
    <div>
        <Card   className='w-[18rem]'>
            <div className={`${item.open ? 'cursor-pointer' : 'cursor-not-allowed'} relative`}>
                <img onClick={handleNavigateToRestaurant} className='w-full h-[10rem] rounded-t-md object-cover'
                src={item.images[1]} alt="" />
                <Chip 
                    size='small'
                    className='absolute top-2 left-2'
                    color={item.open ? "success" : "error"}
                    label={item.open ? "Open Now" : "Closed"}

                />
                <div className='p-4 textPart  lg:flex w-full justify-between'>
                    <div className='space-y-1'>
                        <p  onClick={handleNavigateToRestaurant} className='font-semibold hover:text-blue-400 text-lg'>{item.name}</p>
                        <p className='text-gray-500 text-sm'>
                            {item.description}
                        </p>
                    </div>
                    <div>
                        <IconButton onClick={handleAddToFavorite}>
                            {isPresentInFavorites(auth.favorites , item) ? <FavoriteIcon sx={{color:"red"}} /> : <FavoriteBorderIcon /> }
                        </IconButton>
                    </div>
                </div>
            </div>
            {/* <div className={`${true ? 'cursor-pointer' : 'cursor-not-allowed'} relative`}>
                <img src="https://images.pexels.com/photos/1581554/pexels-photo-1581554.jpeg" alt="" />
            </div>
            <div className={`${true ? 'cursor-pointer' : 'cursor-not-allowed'} relative`}>
                <img src="https://images.pexels.com/photos/239975/pexels-photo-239975.jpeg" alt="" />
            </div>
            <div className={`${true ? 'cursor-pointer' : 'cursor-not-allowed'} relative`}>
                <img src="https://images.pexels.com/photos/2290753/pexels-photo-2290753.jpeg" alt="" />
            </div> */}
        </Card>
    </div>
  )
}

export default RestaurantCart