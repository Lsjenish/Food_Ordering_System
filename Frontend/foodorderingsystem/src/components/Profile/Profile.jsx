import React from 'react'
import ProfileNav from './ProfileNav'

const Profile = () => {
    const [openSideBar, setOpenSideBar] = React.useState(false);
  return (
    <div className='lg:flex justify-between'>
        <div className='sticky h-[80vh] lg:w-[20%] mt-16'>
            <ProfileNav open={openSideBar}/>
        </div>
        <div className='lg:w-[80%] '>

        </div>
    </div>
  )
}

export default Profile