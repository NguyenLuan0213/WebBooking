import React from 'react';
import { Route, Routes, BrowserRouter as Router } from 'react-router-dom';
import Header from './Navbar/Header';
import Home from './Home/Home';
import Login from './Login/login';
// import LoginCus from './Login/loginCus';
import SearchLayout from './Search/Search';
import FeedbackLayout from './Feedback/Feedback';
import MyUserReduce from './reducers/MyUserReduce';
import cookie from "react-cookies";
import CSCS from './CSCS';
import { createContext, useReducer } from 'react';

export const MyUserContext = createContext();

export default function Layout() {
  const [user, state] = useReducer(MyUserReduce, cookie.load('user') || null)
 

  return (
    <div>
      <MyUserContext.Provider value={[user, state]}>
        <Router>
          <div className="h-screen w-full">
            <Header />
            <Routes>
              <Route path="/" element={<Home />} />
              <Route path="/search" element={<SearchLayout />} />
              <Route path="/feedback" element={<FeedbackLayout />} />
              <Route path="/login/" element={<Login />} />
              {/* <Route path="/login/customer" element={<LoginCus />} /> */}
              <Route path="/admin/cscs" element={<CSCS />} />
            </Routes>
          </div>
        </Router>
      </MyUserContext.Provider >
    
    </div>
  );
}
