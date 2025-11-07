import { Divider, FormControl, FormControlLabel, Grid, Radio, RadioGroup, Typography } from '@mui/material'
import React from 'react'
import LocationOnIcon from '@mui/icons-material/LocationOn';
import CalendarTodayIcon from '@mui/icons-material/CalendarToday';
import MenuCard from './MenuCard';
import { useNavigate, useParams } from 'react-router-dom';
import { useDispatch, useSelector } from 'react-redux';
import { getRestaurantById, getRestaurantCategory } from '../State/Restaurant/Action';
import { getMenusItemByRestaurantId } from '../State/Menu/Action';

const foodTypes = [
    { label: "All", value: "all" },
    { label: "Vegiterian Only", value: "vegetarian" },
    { label: "Non Vegiterian Only", value: "non-vegetarian" },
    { label: "Seasonal", value: "seasonal" },
]
const RestaurantDetails = () => {
    const [foodType, setFoodTypes] = React.useState("all");

    const navigate = useNavigate()
    const dispatch = useDispatch()
    const {auth , restaurant , menu} = useSelector(store => store);

    const [selectedCategory , setSelecetedCategory] = React.useState("")

    const jwt = localStorage.getItem("jwt")

    console.log("Restaurant " , restaurant)

    const {id , city} = useParams();

    React.useEffect(() => {
        dispatch(getRestaurantById({jwt , restaurantId:id}))
        dispatch(getRestaurantCategory({jwt , restaurantId:id}))
    },[dispatch, jwt, id])
    const handleFilter = (e , value) => {
        setFoodTypes(e.target.value)
        console.log(e.target.value, e.target.name);
    }
    const handleFilterCategory = (e , value) => {
        setSelecetedCategory(e.target.value)
        console.log(e.target.value, e.target.name);
    }

    React.useEffect(() => {
        dispatch(getMenusItemByRestaurantId({jwt , restaurantId:id
            ,vegetarian : foodType === "vegetarian" ? true : false ,
             nonveg : foodType === "non-vegetarian" ? true : false ,
              seasonal : foodType === "seasonal" ? true : false ,
               foodCategory : selectedCategory}))
    },[selectedCategory ,foodType ])
    return (
        <div className='px-5 lg:px-20'>
            <section>
                <h3 className='text-gray-500 py-2 mt-10 '>Home/india/indian fast food/3</h3>
                <div>
                    <Grid container spacing={2} >
                        <Grid size={{ xs: 12, lg: 6 }} >
                            <img className='h-[40vh] w-screen object-cover' src={restaurant?.restaurant?.images[0]} alt="" />
                        </Grid>
                        <Grid size={{ xs: 12, lg: 6 }}>
                            <img className='w-full h-[40vh] object-cover' src={restaurant?.restaurant?.images[1]} alt="" />
                        </Grid>
                        <Grid size={{ xs: 12, lg: 6 }}>
                            <img className='w-full h-[40vh] object-cover' src={restaurant?.restaurant?.images[2]} alt="" />
                        </Grid>

                    </Grid>
                </div>
                <div className='pt-3 pb-5'>
                    <h1 className='text-4xl font-semibold'>{restaurant.restaurant?.name}</h1>
                    <p className='text-gray-500 mt-1'>{restaurant.restaurant?.description}</p>
                    <div className='space-y-3 mt-3'>
                        <p className='text-gray-500 flex items-center gap-3 text-sm'><LocationOnIcon /><span >Mumbai , Maharastra</span></p>
                        <p className='text-gray-500 flex items-center gap-3 text-sm'><CalendarTodayIcon /><span >{restaurant.restaurant?.openingHours}</span></p>

                    </div>
                </div>
            </section>
            <Divider />
            <section className='pt-[2rem] lg:flex relative'>

                <div className='space-y-10 lg:w-[20%] filter'>
                    <div className='box space-y-5 lg:sticky top-28'>
                        <div>
                            <Typography variant='h5' sx={{ paddingBottom: "1rem" }} >Food Type</Typography>

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
                            <Typography variant='h5' sx={{ paddingBottom: "1rem" }} >Food Category</Typography>

                            <FormControl component={"fieldset"} className='py-10 space-y-5'>
                                <RadioGroup onChange={handleFilterCategory} name='food_category' value={selectedCategory} >
                                    {restaurant?.categories.map((item , index) => 
                                    <FormControlLabel
                                        key={index}
                                        value={item.name}
                                        control={<Radio />}
                                        label={item.name} />
                                    )}
                                </RadioGroup>
                            </FormControl>
                        </div>
                    </div>
                </div>
                <div className='space-y-5 lg:w-[80%] lg:pl-10'>
                    {menu?.menuItems.map((item , idx) => {
                        return <MenuCard key={idx} item={item}/>
                })}
                </div>
            </section>
        </div>
    )
}

export default RestaurantDetails