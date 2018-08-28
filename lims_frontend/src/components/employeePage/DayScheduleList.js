import React from 'react';

export default function DayScheduleList({hour, patientName, analysisName}) {
    return (
        <tr>
            <td>{hour}</td>
            <td>{analysisName}</td>
            <td>{patientName}</td>
        </tr>
    );
}