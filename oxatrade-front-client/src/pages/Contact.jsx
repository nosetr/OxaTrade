import { useState, useEffect } from "react";
import axios from "axios";
import {
  Badge,
  Button,
  Card,
  CardImg,
  CardBody,
  CardTitle,
  CardSubtitle,
  CardText,
  Col,
  Container,
  Row,
  UncontrolledAlert
} from "reactstrap";

const BANNER = "https://i.imgur.com/CaKdFMq.jpg";

export default () => {
  const [post, setPost] = useState(null);

  useEffect(() => {
    axios
      .get(
        "https://baconipsum.com/api/?type=meat-and-filler&paras=4&format=text"
      )
      .then((response) => setPost(response.data));
  }, []);
  return (
    <Container className='mt-5'>
      <Row
        g-0
        className="pt-2 pt-md-5 w-100 px-4 px-xl-0 position-relative"
      >
        <Col
          xs={{ order: 2 }}
          md={{ size: 4, order: 1 }}
          tag="aside"
          className="pb-5 mb-5 pb-md-0 mb-md-0 mx-auto mx-md-0"
        >
          <UncontrolledAlert color="danger" className="d-none d-lg-block">
            <strong>Account not activated.</strong>
          </UncontrolledAlert>
          <Card>
            <CardImg top width="100%" src={BANNER} alt="banner" />
            <CardBody>
              <CardTitle className="h3 mb-2 pt-2 font-weight-bold text-secondary">
                Glad Chinda
              </CardTitle>
              <CardSubtitle
                className="text-secondary mb-3 font-weight-light text-uppercase"
                style={{ fontSize: "0.8rem" }}
              >
                Web Developer, Lagos
              </CardSubtitle>
              <CardText
                className="text-secondary mb-4"
                style={{ fontSize: "0.75rem" }}
              >
                Full-stack web developer learning new hacks one day at a time. Web
                technology enthusiast. Hacking stuffs @theflutterwave.
              </CardText>
              <Button color="success" className="font-weight-bold">
                View Profile
              </Button>
            </CardBody>
          </Card>
        </Col>

        <Col
          xs={{ order: 1 }}
          md={{ size: 7, offset: 1 }}
          tag="section"
          className="py-5 mb-5 py-md-0 mb-md-0"
        >
          {post && (
            <div className="position-relative">
              <span className="d-block pb-2 mb-0 h6 text-uppercase text-info font-weight-bold">
                Editor's Pick
                <Badge
                  pill
                  color="success"
                  className="text-uppercase px-2 py-1 ml-3 mb-1 align-middle"
                  style={{ fontSize: "0.75rem" }}
                >
                  New
                </Badge>
              </span>

              <span className="d-block pb-4 h2 text-dark border-bottom border-gray">
                Getting Started with React
              </span>

              <article
                className="pt-5 text-secondary text-justify"
                style={{ fontSize: "0.9rem", whiteSpace: "pre-line" }}
              >
                {post}
              </article>
            </div>
          )}
        </Col>
      </Row>
    </Container>
  );
};
