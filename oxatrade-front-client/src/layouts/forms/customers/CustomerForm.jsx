import React, { useState } from "react";
import { Form, FormGroup, Label, Input, Button } from 'reactstrap';

const FormNewItem = (props) => {

    const [name, setName] = useState('');
    const [tel, setTel] = useState('');
    const [memo, setMemo] = useState('');

    const submit = () => {
        if(!name.trim() || !tel.trim()) return;
        props.appendContact(name.trim(), tel.trim(), memo.trim())
        setName('')
        setTel('')
        setMemo('')
    }

    return (
        <div className='mt-3'>
            <Form>
                <FormGroup>
                    <Label for="name">Name</Label>
                    <Input
                        id="name"
                        type="text"
                        value={name}
                        onChange={(e) => { setName(e.target.value); }}
                    />
                </FormGroup>
                <FormGroup>
                    <Label for="tel">Tel</Label>
                    <Input
                        id="tel"
                        type='text'
                        value={tel}
                        onChange={(e) => { setTel(e.target.value); }}
                    />
                </FormGroup>
                <FormGroup>
                    <Label for="memo">Memo</Label>
                    <Input
                        id="memo"
                        type="textarea"
                        rows={3}
                        value={memo}
                        onChange={(e) => { setMemo(e.target.value); }}
                    />
                </FormGroup>
                <FormGroup>
                    <Button color='primary' onClick={submit}>Add new</Button>
                </FormGroup>
            </Form>
        </div>
    )
}

export default FormNewItem