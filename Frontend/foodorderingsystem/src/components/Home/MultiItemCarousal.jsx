
import React from 'react'
import CarouselItem from './CarouselItem'
import { topMeal } from './topMeal'
import Slider from 'react-slick'

const MultiItemCarousal = () => {
    const settings = {
    dots: true,
    infinite: true,
    speed: 500,
    slidesToShow: 5,
    slidesToScroll: 1,
    autoplay : true,
    autoplaySpeed: 2000,
    arrows: false,
  };
  return (
    <div>
        <Slider {...settings}>
            {topMeal.map((item) => (
                <CarouselItem image={item.image} title={item.title} />
                ))}
        </Slider>
    </div>
  )
}

export default MultiItemCarousal