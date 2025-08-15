import React from 'react'
import Accordion from '@mui/material/Accordion';
import AccordionSummary from '@mui/material/AccordionSummary';
import AccordionDetails from '@mui/material/AccordionDetails';
import ExpandMoreIcon from '@mui/icons-material/ExpandMore';
import Button from '@mui/material/Button';
import { Checkbox, FormControlLabel, FormGroup } from '@mui/material';

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

const MenuCard = () => {
    const handleCheckBoxChange = (item) => {
        console.log(item);
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
                        <img className='w-[7rem] h-[7rem]' src="https://images.pexels.com/photos/1251198/pexels-photo-1251198.jpeg" alt="" />
                        <div className='space-y-1 lg:space-y-5 lg:max-w-2xl'>
                            <p className='font-semibold text-xl'>Burger</p>
                            <p>₹499</p>
                            <p className='text-gray-400'>nice food here</p>
                        </div>
                    </div>
                </div>
            </AccordionSummary>
            <AccordionDetails>
                <form >
                    <div className='flex flex-wrap gap-5'>
                        {
                            ingredients.map((item) =>
                                <div>
                                    <p>{item.category}</p>
                                    <FormGroup>
                                        {
                                            item.ingredient.map((ingredient) =>
                                                <FormControlLabel control={<Checkbox onChange={() => handleCheckBoxChange(item)} defaultValue={false} />} label={ingredient} />
                                            )
                                        }
                                    </FormGroup>
                                </div>
                            )
                        }
                    </div>
                    <div className='pt-5'>
                        <Button  variant='contained' className='mt-5' disabled = {false} type='submit'>{true ? "Add to Cart" : "Out of Stock"}</Button>
                    </div>
                </form>
            </AccordionDetails>
        </Accordion>
    )
}

export default MenuCard