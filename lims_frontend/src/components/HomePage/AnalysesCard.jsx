import React from 'react';
import { Link } from 'react-router-dom';

export default function AnalyzesCard({ id, type, name, price }) {
    return (
        <tr>
            <th>{name}</th>
            <td>{price} lv</td>
            <td>{type}</td>
            <td><Link to={'/analyzes/' + id}>View Details</Link></td>
        </tr>
    );
}