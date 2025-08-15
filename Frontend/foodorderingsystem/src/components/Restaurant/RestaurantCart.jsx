import React from 'react'
import {Card, Chip, IconButton} from '@mui/material'
import FavoriteIcon from '@mui/icons-material/Favorite';
import FavoriteBorderIcon from '@mui/icons-material/FavoriteBorder';

const RestaurantCart = () => {
  return (
    <div>
        <Card className='w-[18rem]'>
            <div className={`${true ? 'cursor-pointer' : 'cursor-not-allowed'} relative`}>
                <img className='w-full h-[10rem] rounded-t-md object-cover'
                src="https://images.pexels.com/photos/1449773/pexels-photo-1449773.jpeg" alt="" />
                <Chip 
                    size='small'
                    className='absolute top-2 left-2'
                    color={true ? "success" : "error"}
                    label={true ? "Open Now" : "Closed"}

                />
                <div className='p-4 textPart  lg:flex w-full justify-between'>
                    <div className='space-y-1'>
                        <p className='font-semibold text-lg'>Indian Fast Food</p>
                        <p className='text-gray-500 text-sm'>
                            Craving it all? Dive into our global fla...
                        </p>
                    </div>
                    <div>
                        <IconButton >
                            {true ? <FavoriteIcon/> : <FavoriteBorderIcon /> }
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