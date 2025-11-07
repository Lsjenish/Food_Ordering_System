import React from 'react'
import ShoppingBagIcon from '@mui/icons-material/ShoppingBag';
import FavoriteIcon from '@mui/icons-material/Favorite';
import HomeIcon from '@mui/icons-material/Home';
import AccountBalanceWalletIcon from '@mui/icons-material/AccountBalanceWallet';
import NotificationsActiveIcon from '@mui/icons-material/NotificationsActive';
import EventIcon from '@mui/icons-material/Event';
import LogoutIcon from '@mui/icons-material/Logout';
import { Divider, Drawer, useMediaQuery } from '@mui/material';
import { useNavigate } from 'react-router-dom';
import { useDispatch } from 'react-redux';
import { logOutUser } from '../State/Authentication/Action';

const menu = [
    { title: "Orders", icon: <ShoppingBagIcon /> },
    { title: "Favorites", icon: <FavoriteIcon /> },
    { title: "Address", icon: <HomeIcon /> },
    { title: "Payment", icon: <AccountBalanceWalletIcon /> },
    { title: "Notification", icon: <NotificationsActiveIcon /> },
    { title: "Events", icon: <EventIcon /> },
    { title: "LogOut", icon: <LogoutIcon /> }

]

const ProfileNav = ({ open, handleClose }) => {
    const isSmallScreen = useMediaQuery('(max-width: 900px)');
    const navigate = useNavigate();
    const dispatch = useDispatch();
    const handleNavigate = (item) => {
        if(item.title  === "LogOut"){
            dispatch(logOutUser())
            navigate("/")
        }
        else
        navigate(`/my-profile/${item.title.toLowerCase()}`);
    }

    return (
        <div>
            <Drawer onClose={handleClose} variant={isSmallScreen ? 'temporary' : 'permanent'} open={isSmallScreen ? open : true} anchor='left' sx={{ zIndex: 0 , position: 'sticky' }}>
                <div className='w-[50vw] lg:w-[20vw] h-[100vh] pt-16 flex flex-col justify-center text-xl gap-8'>

                    {menu.map((item, idx) => <>
                        <div onClick={() => handleNavigate(item)} className='px-4 flex items-center space-x-5 cursor-pointer'>
                            {item.icon}
                            <span>{item.title}</span>
                        </div>
                        {idx !== menu.length - 1 && <Divider />}
                    </>)}

                </div>
            </Drawer>
        </div>
    )
}

export default ProfileNav