import React from 'react'
import Accordion from '@mui/material/Accordion';
import AccordionSummary from '@mui/material/AccordionSummary';
import AccordionDetails from '@mui/material/AccordionDetails';
import ExpandMoreIcon from '@mui/icons-material/ExpandMore';
import Button from '@mui/material/Button';
import { Checkbox, FormControlLabel, FormGroup } from '@mui/material';
import { categorizeIngredint } from '../util/categorizeIngredient';
import { addItemToCart } from '../State/Cart/Action';
import { useDispatch } from 'react-redux';

const ingredients = [
    {
        category: "Nuts & Seeds",
        ingredient: ["cashews"]
    },
    {
        category: "Protein",
        ingredient: ["Ground Beef", "Chicken Breast"]
    }
]

const MenuCard = ({item}) => {

    const [selectedIngredients , setSelectedIngredients] = React.useState([])
    const dispatch = useDispatch();

    const handleCheckBoxChange = (itemName) => {
        console.log("itemName" , itemName)
        if(selectedIngredients.includes(itemName)){
            setSelectedIngredients(selectedIngredients.filter((ing) => ing !== itemName))
        }
        else{
            setSelectedIngredients([...selectedIngredients , itemName])
        }
    }

    const handleAddItemToCart = () => {
        event.preventDefault();
        console.log("Selected Ingredients" , selectedIngredients)
        const reqData= {
            token : localStorage.getItem("jwt"),
            cartItem : {
                foodId : item.id,
                quantity : 1,
                ingredients : selectedIngredients,

            }
        }
        dispatch(addItemToCart(reqData))
        console.log("req Data" ,reqData)
    }


    return (
        <Accordion>
            <AccordionSummary
                expandIcon={<ExpandMoreIcon />}
                aria-controls="panel1-content"
                id="panel1-header"
            >
                <div className='lg:flex items-center justify-between'>
                    <div className='lg:flex items-center lg:gap-5'>
                        <img className='w-[7rem] h-[7rem]' src={item.images[0]} alt="" />
                        <div className='space-y-1 lg:space-y-5 lg:max-w-2xl'>
                            <p className='font-semibold text-xl'>{item.name}</p>
                            <p>₹{item.price}</p>
                            <p className='text-gray-400'>{item.description}</p>
                        </div>
                    </div>
                </div>
            </AccordionSummary>
            <AccordionDetails>
                <form onSubmit={handleAddItemToCart} className='space-y-5' method='put'>
                    <div className='flex flex-wrap gap-5'>
                        {
                            Object.keys(categorizeIngredint(item.ingredients)).map((category) =>
                                <div>
                                    <p>{category}</p>
                                    <FormGroup>
                                        {
                                            categorizeIngredint(item.ingredients)[category].map((item , idx) =>
                                                <FormControlLabel key={idx} control={<Checkbox onChange={() => handleCheckBoxChange(item.name)} defaultValue={false} />} label={item.name} />
                                            )
                                        }
                                    </FormGroup>
                                </div>
                            )
                        }
                    </div>
                    <div className='pt-5'>
                        <Button variant='contained' className='mt-5' disabled = {false} type='submit'>{true ? "Add to Cart" : "Out of Stock"}</Button>
                    </div>
                </form>
            </AccordionDetails>
        </Accordion>
    )
}

export default MenuCard