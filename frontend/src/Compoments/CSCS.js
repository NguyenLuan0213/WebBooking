import React, { useState, useEffect } from 'react';
import { endpoints, authAPI } from './configs/Api';
import { Link } from 'react-router-dom';
import Loading from './layout/Loading';
import { Container, Row, Col, Table } from 'react-bootstrap';

const CSCS = () => {
    const [cscs, setCSCS] = useState(null);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        const fetchCSCS = async () => {
            try {
                const response = await authAPI().get(endpoints.CSCS);
                setCSCS(response.data);
                setLoading(false);
            } catch (error) {
                console.error('Error fetching CSCS:', error);
                setLoading(false);
            }
        };

        fetchCSCS();
        console.log(cscs);
    }, []);

    if (cscs === null) {
        return <Loading />;
    }

    return (
        <Container>
            <h1>Coachstripcoachseat List</h1>
            <Link to="/create-cscs">Create New CSCS</Link>
            <Row>
                <Col>
                    <Table striped bordered hover>
                        <thead>
                            <tr>
                                <th>Tên Vé</th>
                                <th>Số ghế</th>
                                <th>Tên Số Xe</th>
                                <th>Tên Chuyến Đi</th>
                                <th>Số tiền</th>
                                <th>Người Bán</th>
                                <th>Thông tin vé</th>
                                <th>Departure Time</th>
                                {/* Thêm các cột khác tùy theo cấu trúc của đối tượng Coachstripcoachseat */}
                            </tr>
                        </thead>
                        <tbody>
                            {cscs.map((cscsItem) => (
                                <tr key={cscsItem.idCSCS}>
                                    <td>{cscsItem.idCSCS}</td>
                                    <td>{cscsItem.nameSeat}</td>
                                    <td>{cscsItem.idCoach.numberCoach}</td>
                                    <td>{cscsItem.idCoachStrips.nameCS}</td>
                                    <td>{cscsItem.price}</td>
                                    <td>{cscsItem.idStaff.nameStaff}</td>
                                    <td>{cscsItem.statusSeat}</td>
                                    <td>{cscsItem.departureTime}</td>
                                    {/* Thêm các dữ liệu khác tương ứng */}
                                </tr>
                            ))}
                        </tbody>
                    </Table>
                </Col>
            </Row>
        </Container>
    );
};

export default CSCS;
