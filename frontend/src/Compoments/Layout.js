import React from 'react';
import { Route, Routes, BrowserRouter as Router } from 'react-router-dom';
import NavbarHeader from './Navbar/Navbar';
import Home from './Home/Home';
import LoginLayout from './Login/login';
import SearchLayout from './Search/Search';
import FeedbackLayout from './Feedback/Feedback';
import MyUserReduce from './reducers/MyUserReduce.js';
import cookie from "react-cookies";
import CSCS from './CSCS.js';
import { createContext, useReducer } from 'react';

export const MyUserContext = createContext();
export default function Layout() {
  const [user, state] = useReducer(MyUserReduce, cookie.load('user') || null)
  return (
    <div>
      <MyUserContext.Provider value={[user, state]}>
        <Router>
          <div className="h-screen w-full">
            <NavbarHeader />
            <Routes>
              <Route path="/" element={<Home />} />
              <Route path="/search" element={<SearchLayout />} />
              <Route path="/feedback" element={<FeedbackLayout />} />
              <Route path="/login" element={<LoginLayout />} />
              <Route path="/api/cscs" element={<CSCS />} />
            </Routes>
          </div>
        </Router>
      </MyUserContext.Provider >
    </div>
  );
}
