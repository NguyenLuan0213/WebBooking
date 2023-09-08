import React from 'react'
import mdata from './MOCK_DATA (1).json'
import TableViewData from './TableViewData'
import { Fragment, useState } from 'react'
import BookingPopup from './BookingPopup'
export default function SearchLayout() {

  const [isOpen, setIsOpen] = useState(false)

  function closeModal() {
    setIsOpen(false)
  }

  function openModal() {
    setIsOpen(true)
  }

  console.log(mdata)

  const columns = [
    {
      header:"Trạm",
      accessorKey:"buses"
    },
    {
    header:"Nơi xuất phát",
    accessorKey:"location"
    },
    {
      header:"Nơi đến",
      accessorKey:"location"
    },
    {
      header:"Loại vé",
      accessorKey:"first_name"
    },
    {
      header:"Giá vé",
      accessorKey:"id"
    },
    

 
  {
    header:"Booking",
    accessorKey:"",
    cell: ({row}) =>(
      <button onClick={openModal} className='px-10 py-1 bg-blue-300 hover:bg-blue-500'>Booking</button>
      
    )
  },
]


  return (
    <div className='table'>
      <TableViewData  columns={columns} mData={mdata}/>
      {isOpen !== false ? (
        <BookingPopup isOpen={isOpen} setIsOpen={setIsOpen} closeModal={closeModal}l/>
      ): null}
    </div>
  )
}
