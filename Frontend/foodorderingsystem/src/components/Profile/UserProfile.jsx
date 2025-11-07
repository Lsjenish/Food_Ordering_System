import React from 'react'
import AccountCircleIcon from '@mui/icons-material/AccountCircle';
import { Button } from '@mui/material';
const UserProfile = () => {
    const handleLogout = () => {
        console.log("Logout");
    }
  return (
    <div className='min-h-[80vh] flex flex-col justify-center items-center text-center'>
        <div className='flex flex-col justify-center items-center'>
            <AccountCircleIcon sx={{fontSize : "9rem"}} />
            <h1 className='text-2xl font-semibold py-5'>Code with Jenish</h1>
            <p >Email: jeniston2005@gmail.com</p>
            <Button variant='contained' onClick={handleLogout} sx={{margin:"2rem 0rem"}}> LogOut </Button>
        </div>
    </div>
  )
}

export default UserProfile