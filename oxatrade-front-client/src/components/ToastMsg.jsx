import { Toast, ToastHeader, ToastBody, Button } from 'reactstrap'

const ToastMsg = ({ title, text }) => {
    if (title && text) {
        return (
            <div className="position-fixed bottom-0 end-0 p-3" style={{ zIndex: 11 }}>
                <Toast className='align-items-center' aria-live="assertive" aria-atomic="true">
                    <div className='toast-header'>
                        <strong className='me-auto'>{title}</strong>
                        <Button className='btn-close' data-bs-dismiss="toast" aria-label="Close" />
                    </div>
                    <ToastBody>{text}</ToastBody>
                </Toast>
            </div>
        )
    } else if (text) {
        return (
            <div className="position-fixed bottom-0 end-0 p-3" style={{ zIndex: 11 }}>
                <Toast className='align-items-center text-white bg-primary border-0' aria-live="assertive" aria-atomic="true">
                    <div className="d-flex">
                        <ToastBody>{text}</ToastBody>
                        <Button className="btn-close btn-close-white me-2 m-auto" aria-label='Close' data-bs-dismiss="toast" />
                    </div>
                </Toast>
            </div >
        )
    }
}

export default ToastMsg;