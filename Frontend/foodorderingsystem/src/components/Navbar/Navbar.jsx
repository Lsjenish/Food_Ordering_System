import IconButton from '@mui/material/IconButton'
import React from 'react'
import SearchIcon from '@mui/icons-material/Search';
import Avatar from '@mui/material/Avatar';
import ShoppingCartIcon from '@mui/icons-material/ShoppingCart';
import Badge from '@mui/material/Badge';

import './NavBar.css'
import { Box } from '@mui/material';
import { Person } from '@mui/icons-material';
import { useNavigate } from 'react-router-dom';
import { useSelector } from 'react-redux';

const Navbar = () => {
  const {cart ,auth} = useSelector(store => store)
  const navigate = useNavigate();
  const handleAvatarClick = ()=>{
    if(auth.user.role === "ROLE_CUSTOMER"){
      navigate("/my-profile")
    }
  }
  return (
    <Box className='px-5 sticky top-0 z-50 py-[.8rem] bg-[#e91e63] lg:px-20 flex justify-between'>
      {/* Logo and Search Bar */}
          <div className='lg:mr-10 cursor-pointer flex items-center space-x-4'>
            <li onClick={() => navigate("/")} className='logo select-none font-semibold text-gray-300 text-2xl'>
              Munch Food
            </li>
          </div>
          <div className='flex items-center space-x-2 lg:space-x-10'>
            <div className=''>
              <IconButton>
                  <SearchIcon sx={{ fontSize : "1.5rem" }} />
              </IconButton>
            </div>
            <div className=''>
            {auth.user ? <Avatar onClick={handleAvatarClick} sx={{bgcolor : "white" , color : "#e91e63" }}>{auth.user?.fullName[0].toUpperCase()}</Avatar> 
            : <IconButton onClick={() => navigate("/account/login")}>
              <Person />
              </IconButton>}
            </div>
            <div className=''>
              <IconButton onClick={() => navigate("/cart")}>
                  <Badge color="secondary" badgeContent={cart.cart?.items.length} >
                    <ShoppingCartIcon sx={{ fontSize : "1.5rem" }} />
                  </Badge>
                  
              </IconButton>
            </div>
          </div>

    </Box>
  )
}

export default Navbar