import axios from 'axios';
import React, { useState, useEffect } from 'react';

import TableView from '../layouts/tables/customers/CustomersTable';
import FormNewItem from '../layouts/forms/customers/CustomerForm';

import {
  Container, Card, CardHeader, CardBody
} from 'reactstrap';

const Home = () => {
  
  //const keys = require("./config/keys").default;
  //import { API_BASE_URL } from './constants';
  //const HOST = API_BASE_URL + '/api/';
  const HOST = 'http://localhost:8080/api/';

  const [items, setItems] = useState([])

  useEffect(() => {
    axios.get(HOST + 'contacts')
      .then(res => {
        const data = [];
        res.data._embedded.contacts.forEach(item => {
          data.push(
            {
              id: item.id,
              name: item.fullName,
              tel: item.telephone,
              memo: item.notes
            }
          )
        })
        setItems(data)
      })
  }, [])

  const appendContact = (name, tel, memo) => {
    const temp = {
      fullName: name,
      telephone: tel,
      notes: memo
    };

    axios.post(HOST + 'contacts', temp)
      .then(e => {
        temp.id = e.data.id;
        setItems([...items, temp]);
      });
  }

  const removeContact = (id) => {
    const url = `${HOST}contacts/${id}`;
    axios.delete(url);
    setItems(items.filter(item => item.id !== id))
  }

  return (
    <Container className='mt-5'>
      <Card>
        <CardHeader>
          <h1>Adressen:</h1>
        </CardHeader>
        <CardBody>
          <TableView data={items} removeContact={removeContact} />
          <FormNewItem appendContact={appendContact} />
        </CardBody>
      </Card>
    </Container>
  );
};

export default Home;
