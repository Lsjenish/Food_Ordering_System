import { Box, Button, Card, Divider, Grid, Modal, TextField } from '@mui/material'
import React from 'react'
import CartItem from './CartItem'
import AddressCart from './AddressCart'
import AddLocationAltIcon from '@mui/icons-material/AddLocationAlt';
import { ErrorMessage, Field, Form, Formik } from 'formik'
import * as Yup from 'yup'

const items = [1, 1]
const style = {
    position: 'absolute',
    top: '50%',
    left: '50%',
    transform: 'translate(-50%, -50%)',
    width: 400,
    bgcolor: 'background.paper',
    outline: 'none',
    boxShadow: 24,
    p: 4,
};

const initialValues = {
    streetAddress: "",
    state: "",
    pincode: "",
    city: ""
}

// const validationSchema = Yup.object.shape({
//     streetAddress: Yup.string().required("Street Address is required"),
//     state: Yup.string().required("State is required"),
//     pincode: Yup.string().required("Pincode is required").matches(/^[0-9]{6}$/, "Pincode must be 6 digits"),
//     city: Yup.string().required("City is required")
// })

const handleSubmit = (values) => {
    console.log("Form Values:", values);
}
const Cart = () => {
    const createOrderUsingSelectedAddress = () => { }
    const handleOpenAddressModel = () => setOpen(true);
    const [open, setOpen] = React.useState(false);
    const handleClose = () => setOpen(false);

    return (
        <>
            <main className='lg:flex justify-between'>
                <section className='lg:w-[30%] space-y-6 lg:min-h-screen pt-10'>
                    {items.map((item, idx) => <CartItem key={idx} />)}
                    <Divider />
                    <div className='billDetails px-5 text-5m'>
                        <p className='font-extralight py-5'>Bill Detail</p>
                        <div className='space-y-3'>
                            <div className='flex justify-between text-gray-400'>
                                <p>Item Total</p>
                                <p>₹ 1000</p>
                            </div>
                            <div className='flex justify-between text-gray-400'>
                                <p>Delivery Fee</p>
                                <p>₹ 50</p>
                            </div>
                            <div className='flex justify-between text-gray-400'>
                                <p>Discount</p>
                                <p>- ₹ 100</p>
                            </div>
                            <div className='flex justify-between text-gray-400'>
                                <p>GST and Restaurant Charges</p>
                                <p>₹ 33</p>
                            </div>
                            <Divider />
                        </div>
                        <div className='flex justify-between text-gray-400'>
                            <p className='font-semibold'>Total</p>
                            <p className='font-semibold'>₹ 983</p>
                        </div>
                    </div>
                </section>
                <Divider orientation='vertical' flexItem />
                <section className='lg:w-[70%] pt-10 flex justify-center pb-10 lg:pb-0'>
                    <div >
                        <h1 className='text-2xl py-10 font-semibold text-gray-600 text-center'>
                            Choose Delivery Address
                        </h1>
                        <div className='flex flex-wrap gap-5 justify-center'>
                            {[1, 1, 1].map((item, idx) => <AddressCart key={idx} item={item} showButton={true} handleSelectAddress={createOrderUsingSelectedAddress} />)}
                            <Card className='flex gap-5 w-63 p-5'>
                                <AddLocationAltIcon />
                                <div className='space-y-3 text-gray-500'>
                                    <h1 className='text-lg font-semibold text-white'>Add new Address</h1>
                                    {<Button variant='outlined' fullWidth onClick={handleOpenAddressModel}>Add</Button>}
                                </div>
                            </Card>
                        </div>
                    </div>
                </section>
                <Modal
                    open={open}
                    onClose={handleClose}
                    aria-labelledby="modal-modal-title"
                    aria-describedby="modal-modal-description"
                >
                    {/* error={!ErrorMessage("streetAddress")} helperText={
                                        <ErrorMessage>
                                            {(msg) => <span className='text-red-600'>{msg}</span>}
                                        </ErrorMessage>
                                    } */}

                    {/* validationSchema={validationSchema} */}
                    <Box sx={style}>
                        <Formik initialValues={initialValues} onSubmit={handleSubmit}>
                            <Form>
                                <Grid container spacing={2}>
                                    <Grid item xs={12}>
                                        <Field as={TextField} name="streetAddress" label="Street Address" fullWidth variant="outlined" />

                                    </Grid>
                                    <Grid item xs={12}>
                                        <Field as={TextField} name="state" label="state" fullWidth variant="outlined" />

                                    </Grid>
                                    <Grid item xs={12}>
                                        <Field as={TextField} fullWidth name="city" label="city" variant="outlined" />

                                    </Grid>
                                    <Grid item xs={12}>
                                        <Field as={TextField} name="pincode" label="pincode" fullWidth variant="outlined" />

                                    </Grid>
                                    <Grid item xs={12}>
                                        <Button type='submit' variant='contained' fullWidth color='primary'>Save Address</Button>
                                    </Grid>
                                </Grid>
                            </Form>

                        </Formik>
                    </Box>
                </Modal>

            </main>

        </>
    )
}

export default Cart