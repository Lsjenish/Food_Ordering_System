import React from 'react'
import { Button, Card, CardActions, CardContent, CardMedia, IconButton, Typography } from '@mui/material'
import DeleteIcon from '@mui/icons-material/Delete';
const EventCart = () => {
  return (
    <Card sx={{width:345}}>
        <CardMedia sx={{height:345}} image='https://cdn.pixabay.com/photo/2017/03/28/07/54/chinese-2181270_1280.jpg' />
        <CardContent >
            <Typography variant='h5' >Indian Fast Food</Typography>
            <Typography variant='body2' >50% offer for first order</Typography>
            <div className='py-2 space-y-2'>
                <p>{"mumbai"}</p>
                <p className='text-sm text-blue-500'>February 14, 2025 12:00 AM</p>
                <p className='text-sm text-red-500'>February 15, 2025 12:00 AM</p>
            </div>
        </CardContent>
        {false && <CardActions>
            <IconButton>
                <DeleteIcon color='error' />
            </IconButton>
        </CardActions>}
    </Card>
  )
}

export default EventCart