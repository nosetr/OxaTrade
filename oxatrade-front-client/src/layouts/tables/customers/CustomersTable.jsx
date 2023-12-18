import React from 'react'
import TableRowItem from './components/TableRowItem';
import { Table } from 'reactstrap';

const TableView = (props) => {
    return (
      <Table striped hover responsive>
        <thead>
          <tr>
            <th scope='col'>#</th>
            <th scope='col'>Name</th>
            <th scope='col'>Tel.</th>
            <th scope='col'>Notes</th>
            <th></th>
          </tr>
        </thead>
        <tbody>
          {
            props.data.map(item => (
              <TableRowItem
                key={item.id}
                removeContact={props.removeContact}
                contact={item}
              />
            ))
          }
        </tbody>
      </Table>
    );
}

export default TableView;