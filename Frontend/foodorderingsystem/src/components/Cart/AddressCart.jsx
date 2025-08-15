import React from 'react'
import HomeIcon from '@mui/icons-material/Home';
import { Button, Card } from '@mui/material';

const AddressCart = ({item , showButton , handleSelectAddress }) => {
 
  return (
    <Card className='flex gap-5 w-63 p-5'>
        <HomeIcon />
        <div className='space-y-3 text-gray-500'>
            <h1 className='text-lg font-semibold text-white'>Home</h1>
            <p className='text-sm text-gray-500'>Jeninston, 1234 Street Name, City, State, 12345</p>
            {showButton &&(<Button variant='outlined' fullWidth onClick={() => handleSelectAddress(item)}>Select</Button>)}
        </div>
    </Card>
  )
}

export default AddressCart