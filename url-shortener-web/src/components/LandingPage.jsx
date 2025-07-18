import React from 'react'
import Card from './Card'

const LandingPage = () => {
  return (
    <div className="min-h-[calc(100vh-64px)]  lg:px-14 sm:px-8 px-4">
      <div className="lg:flex-row flex-col    lg:py-5   pt-16   lg:gap-10 gap-8 flex justify-between items-center">
        <div className=" flex-1">
          <h1 className='font-bold font-roboto text-slate-800 md:text-5xl text-3xl md:leading-[55px]'> 
            Linklytics Simplifies URL Shortening For Efficient Sharing.</h1>

          <p className="text-slate-700 text-sm my-5">
            Linklytics streamlines the process of URL shortening, making sharing
            links effortless and efficient. With its user-friendly interface,
            Linklytics allows you to generate concise, easy-to-share URLs in
            seconds. Simplify your sharing experience with Linklytics today.
          </p>

          <div className="flex items-center gap-3">
            <button className='bg-custom-gradient w-40 text-white rounded-md py-2'>Manage Links</button>
            <button className="border-btnColor border w-40 text-btnColor rounded-md  py-2 ">Short Link</button>
          </div>
        </div>

        <div className="   flex-1 flex   justify-center w-full">
          <img
            className="sm:w-[480px] w-[400px] object-cover rounded-md"
            src="/images/img2.png"
            alt=""
          />
        </div>
      </div>

      <div className="sm:pt-12 pt-7">
          <p className="text-slate-800 font-roboto font-bold lg:w-[60%] md:w-[70%] sm:w-[80%] mx-auto text-3xl text-center">
            Trusted by individuals and teams at the world best companies{" "}
          </p>
          <div className="pt-4 pb-7 grid lg:gap-7 gap-4 xl:grid-cols-4 lg:grid-cols-3 sm:grid-cols-2 grid-cols-1 mt-4">
            <Card
              title="Simple URL Shortening"
              desc="Experience the ease of creating short, memorable URLs in just a few clicks. Our intuitive interface and quick setup process ensure you can start shortening URLs without any hassle."
            />
            <Card
              title="Powerful Analytics"
              desc="Gain insights into your link performance with our comprehensive analytics dashboard. Track clicks, geographical data, and referral sources to optimize your marketing strategies."
            />
            <Card
              title="Enhanced Security"
              desc="Rest assured with our robust security measures. All shortened URLs are protected with advanced encryption, ensuring your data remains safe and secure."
            />
            <Card
              title="Fast and Reliable"
              desc="Enjoy lightning-fast redirects and high uptime with our reliable infrastructure. Your shortened URLs will always be available and responsive, ensuring a seamless experience for your users."
            />
          </div>
        </div>
    </div>
  )
}

export default LandingPage
