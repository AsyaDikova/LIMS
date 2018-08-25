import React from 'react';
import { Link } from 'react-router-dom';

export default function AnalyzesCard({ id, type, name, price }) {
    return (
        <article className="analysesCard">
            <p>Analyses name: {name}</p>
            <p>price: {price}</p>
            <div>type: {type}</div>
            <Link to={'/analyzes/' + id}>View Details</Link>
        </article>
    );
}