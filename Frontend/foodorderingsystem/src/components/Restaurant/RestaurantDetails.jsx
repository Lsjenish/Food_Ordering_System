import { Divider, FormControl,FormControlLabel , Grid, Radio , RadioGroup, Typography } from '@mui/material'
import React from 'react'
import LocationOnIcon from '@mui/icons-material/LocationOn';
import CalendarTodayIcon from '@mui/icons-material/CalendarToday';
import MenuCard from './MenuCard';

const categories = [
    "pizza",
    "burger",
    "chicken",
    "biriyani",
    "rice",
]
const foodTypes = [
    {label:"All" , value:"all"},
    {label:"Vegiterian Only" , value:"vegotarian"},    
    {label:"Non Vegiterian Only" , value:"non-vegetarian"},
    {label:"Seasonal" , value:"seasonal"},
]
const RestaurantDetails = () => {
    const [foodType, setFoodTypes] = React.useState("all");

    const handleFilter = (e) => { 
        console.log(e.target.value , e.target);
    }

    const menu = [1,1,1,1,1,1,1,1,1,1]

  return (
    <div className='px-5 lg:px-20'>
        <section>
            <h3 className='text-gray-500 py-2 mt-10 '>Home/india/indian fast food/3</h3>
            <div>
                <Grid container spacing={2} >
                    <Grid item xs={12} > 
                        <img className='h-[40vh] w-screen object-cover' src="https://images.pexels.com/photos/260922/pexels-photo-260922.jpeg" alt="" />
                    </Grid>
                    <Grid item xs={12} lg = {6}> 
                        <img className='w-full h-[40vh] object-cover' src="https://images.pexels.com/photos/1581554/pexels-photo-1581554.jpeg" alt="" />
                    </Grid>
                    <Grid item xs={12} lg = {6}> 
                        <img className='w-full h-[40vh] object-cover' src="https://images.pexels.com/photos/239975/pexels-photo-239975.jpeg" alt="" />
                    </Grid>
                    
                </Grid>
            </div>
            <div className='pt-3 pb-5'>
                <h1 className='text-4xl font-semibold'>Indian Fast Food</h1>
                <p className='text-gray-500 mt-1'>Craving it all? Dive into our global flavors with Indian Fast Food. From spicy curries to crispy samosas, we bring the taste of India to your table. Order now and satisfy your cravings!</p>
                <div className='space-y-3 mt-3'>
                    <p className='text-gray-500 flex items-center gap-3 text-sm'><LocationOnIcon /><span >Mumbai , Maharastra</span></p>
                    <p className='text-gray-500 flex items-center gap-3 text-sm'><CalendarTodayIcon /><span >Mon - Sun: 9:00 AM - 9:00 PM (Today)</span></p>
            
                </div>
                </div>
        </section>
        <Divider />
        <section className='pt-[2rem] lg:flex relative'>

            <div className='space-y-10 lg:w-[20%] filter'>
                <div className='box space-y-5 lg:sticky top-28'>
                    <div>
                        <Typography variant='h5' sx={{paddingBottom : "1rem"}} >Food Type</Typography>

                        <FormControl component="fieldset" className='py-10 space-y-5'>
                            <RadioGroup onChange={handleFilter} name='food-type' value={foodType} >
                                {foodTypes.map((type) => <FormControlLabel 
                                key={type.value}
                                value={type.value} 
                                control={<Radio />} 
                                label={type.label} />
                                )}
                            </RadioGroup>
                        </FormControl>
                    </div>
                    <Divider />
                    <div className='mt-5'>
                        <Typography variant='h5' sx={{paddingBottom : "1rem"}} >Food Category</Typography>

                        <FormControl component="fieldset" className='py-10 space-y-5'>
                            <RadioGroup onChange={handleFilter} name='food-type' value={foodType} >
                                {categories.map((item) => <FormControlLabel 
                                key={item}
                                value={item} 
                                control={<Radio />} 
                                label={item} />
                                )}
                            </RadioGroup>
                        </FormControl>
                    </div>
                </div>
            </div>
            <div className='space-y-5 lg:w-[80%] lg:pl-10'>
                {menu.map((item) => <MenuCard />)}
            </div>
        </section>
    </div>
  )
}

export default RestaurantDetails