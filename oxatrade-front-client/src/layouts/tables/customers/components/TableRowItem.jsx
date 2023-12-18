import { Button } from 'reactstrap';

const TableRowItem = (props) => {
    return (
        <tr>
            <td scope='row'>{props.contact.id}</td>
            <td>{props.contact.name}</td>
            <td>{props.contact.tel}</td>
            <td>{props.contact.memo}</td>
            <td>
                <Button color='primary' onClick={()=>props.removeContact(props.contact.id)}>
                    Delete
                </Button>
            </td>
        </tr>
    )
}

export default TableRowItem