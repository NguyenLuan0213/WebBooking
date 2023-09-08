import { useContext, useEffect, useState } from 'react';
import Container from 'react-bootstrap/Container';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';
import { Link, Navigate } from 'react-router-dom';
import MyUserReduce from '../reducers/MyUserReduce';
import { Button, NavDropdown } from 'react-bootstrap';
import { MyUserContext } from '../Layout';

const Header = () => {
    const [user, state] = useContext(MyUserContext)
    const [exit, setExit] = useState(false)
    const logout = () => {
        state({
            'Type': 'logout'
        })
    }

    return (
        <Navbar bg="light" variant="light">
            <Container>
                <Navbar.Brand href="/">WebBookingServer</Navbar.Brand>
                <Nav className="me-auto">
                    {user !== null && user.roles === 'ROLE_ADMIN' && (
                        <>
                            <Button><Link to="/search" className="nav-link">Search</Link></Button>
                            <Link to="/feedback" className="nav-link">FeedBack</Link>
                            <Link to="/admin/cscs" className="nav-link">CSCS</Link>
                        </>
                    )}
                </Nav>
                <Navbar.Collapse className="justify-content-end">
                    {user === null || user === undefined ? (
                        <>
                            <Link to="/register" className="btn btn-danger mx-2">Đăng Ký</Link>
                            <Link to="/login/" className="btn btn-success">Đăng Nhập</Link>
                        </>
                    ) : (
                        <>
                            <span className="navbar-text mx-2">Chào {user.nameStaff}</span>
                            <button onClick={logout} className="btn btn-secondary">Đăng xuất</button>
                        </>
                    )}
                </Navbar.Collapse>
            </Container>
        </Navbar>
    );
}

export default Header