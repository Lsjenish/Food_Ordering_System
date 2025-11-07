import React from 'react'
import ProfileNav from './ProfileNav'
import { Route, Routes } from 'react-router-dom';
import Orders from './Orders';
import Address from './Address';
import UserProfile from './UserProfile';
import Favorites from './Favorites';
import Events from './Events';

const Profile = () => {
    const [openSideBar, setOpenSideBar] = React.useState(false);
  return (
    <div className='lg:flex justify-between select-none'>
        <div className='sticky h-[80vh] lg:w-[20%] mt-16'>
            <ProfileNav open={openSideBar}/>
        </div>
        <div className='lg:w-[80%] '>
        <Routes>
            <Route path='/' element={<UserProfile />}/>
            <Route path='/orders' element={<Orders />} />
            <Route path='/favorites' element={<Favorites />} />
            <Route path='/address' element={<Address />} />
            <Route path='/events' element={<Events />} />
        </Routes>
        </div>
    </div>
  )
}

export default Profile