import React, { useMemo, useState } from "react";
import {
  useReactTable,
  getCoreRowModel,
  getPaginationRowModel,
  flexRender,
  getSortedRowModel,
  getFilteredRowModel,
} from "@tanstack/react-table";
import { FaAngleDown, FaAngleUp } from "react-icons/fa6";

export default function TableViewData({ columns, mData }) {
  const data = useMemo(() => mData, [mData]);
  const [sorting, setSorting] = useState([]);
  const [filtering, setFiltering] = useState("");

  const table = useReactTable({
    data,
    columns,
    getCoreRowModel: getCoreRowModel(),
    getPaginationRowModel: getPaginationRowModel(),
    getSortedRowModel: getSortedRowModel(),
    getFilteredRowModel: getFilteredRowModel(),
    state: {
      sorting: sorting,
      globalFilter: filtering,
    },
    onSortingChange: setSorting,
    onGlobalFilterChange: setFiltering,
  });
  return (
    <div className="w-11/12 h-auto m-auto " class>
      <div className="flex flex-col overflow-x-auto border border-gray-500">
        <div className="sm:-mx-6 lg:-mx-8">
          <div className="inline-block min-w-full py-2 sm:px-6 lg:px-8">
            <div className="overflow-x-auto">
              <div className="text-left">
                <label htmlFor="global">Tìm kiếm</label>
                <input
                  name="global"
                  id="global"
                  type="text"
                  placeholder="Tìm kiếm"
                  className="border border-black outline-none ml-2 px-1"
                  value={filtering}
                  onChange={(e) => setFiltering(e.target.value)}
                />
              </div>

              <table className="min-w-full text-left text-sm font-light">
                <thead className="border-b font-medium dark:border-neutral-500">
                  {table.getHeaderGroups().map((headerGroup) => (
                    <tr key={headerGroup.id}>
                      {headerGroup.headers.map((header) => (
                        <th
                          scope="col"
                          className="px-6 py-4 cursor-pointer"
                          key={header.id}
                          onClick={header.column.getToggleSortingHandler()}
                        >
                          {flexRender(
                            header.column.columnDef.header,
                            header.getContext()
                          )}
                          {
                            {
                              asc: <FaAngleUp className="inline-block ml-2" />,
                              desc: (
                                <FaAngleDown className="inline-block ml-2" />
                              ),
                            }[header.column.getIsSorted() ?? null]
                          }
                        </th>
                      ))}
                    </tr>
                  ))}
                </thead>
                <tbody>
                    {table.getRowModel().rows.map((row) => (
                        <tr
                          key={row.id}
                          className="border-b dark:border-neutral-500"
                        >
                          {row.getVisibleCells().map((cell) => (
                            <td
                              key={cell.id}
                              className="whitespace-nowrap px-6 py-4 font-medium"
                            >
                              {flexRender(cell.column.columnDef.cell, {
                                ...cell.getContext(),
                                value: cell.value,
                              })}
                            </td>
                          ))}
                        </tr>
                      ))}
                    </tbody>
                    {/* <tfoot>
                      <tr>
                        <td>ID</td>
                      </tr>
                    </tfoot> */}
                  </table>
                </div>
              </div>
            </div>
          </div>
          <div>
            <button
              className="px-2 py-1 bg-blue-300 mx-2 mt-3"
              onClick={() => table.setPageIndex(0)}
            >
              Đầu
            </button>
            <button
              className="px-2 py-1 bg-blue-300 mx-2 mt-3"
              disabled={!table.getCanPreviousPage()}
              onClick={() => table.previousPage()}
            >
              Trước
            </button>
            <button
              className="px-2 py-1 bg-blue-300 mx-2 mt-3"
              disabled={!table.getCanNextPage()}
              onClick={() => table.nextPage()}
            >
              Sau
            </button>
            <button
              className="px-2 py-1 bg-blue-300 mx-2 mt-3"
              onClick={() => table.setPageIndex(table.getPageCount() - 1)}
            >
              Cuối
            </button>
          </div>
        </div>
      );
    }