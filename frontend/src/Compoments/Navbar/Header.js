import { useContext, useEffect, useState } from "react";
import Container from "react-bootstrap/Container";
import Nav from "react-bootstrap/Nav";
import Navbar from "react-bootstrap/Navbar";
import { Link, Navigate } from "react-router-dom";
import { MyUserContext } from "../Layout";
import { Button } from "react-bootstrap";
import { FaBorderAll } from "react-icons/fa6";
const Header = () => {
  const [user, state] = useContext(MyUserContext);
  const [exit, setExit] = useState(false);
  const logout = () => {
    state({
      Type: "logout",
    });
  };

  return (
    <div className="header">
      <div className="container">
        <Navbar.Brand href="/" className="logo">
          Home
        </Navbar.Brand>

        {user !== null && user.roles === "ROLE_ADMIN" && <></>}
        <div className="button">
          <Button className="nav-link">
            <Link to="/search" className="text">
              Search
            </Link>
          </Button>
          <Button className="nav-link">
            <Link to="/feedback" className="text">
              FeedBack
            </Link>
          </Button>
          <Button className="nav-link">
            <Link to="/api/cscs" className="text">
              CSCS
            </Link>
          </Button>
        </div>
        <Navbar.Collapse className="justify-content-end">
          {user === null || user === undefined ? (
            <>
              <Link to="/register" className="btn btn-danger mx-2">
                Đăng Ký
              </Link>
              <Link to="/login/" className="btn btn-success">
                Đăng Nhập
              </Link>
            </>
          ) : (
            <>
              <span className="navbar-text mx-2">Chào {user.nameStaff}</span>
              <button onClick={logout} className="btn btn-secondary">
                Đăng xuất
              </button>
            </>
          )}
        </Navbar.Collapse>
      </div>
    </div>
  );
};

export default Header;
