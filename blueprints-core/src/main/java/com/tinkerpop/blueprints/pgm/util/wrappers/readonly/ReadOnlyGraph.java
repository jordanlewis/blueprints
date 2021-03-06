package com.tinkerpop.blueprints.pgm.util.wrappers.readonly;

import com.tinkerpop.blueprints.pgm.Edge;
import com.tinkerpop.blueprints.pgm.Graph;
import com.tinkerpop.blueprints.pgm.Vertex;
import com.tinkerpop.blueprints.pgm.WrappableGraph;
import com.tinkerpop.blueprints.pgm.impls.StringFactory;
import com.tinkerpop.blueprints.pgm.util.wrappers.readonly.util.ReadOnlyEdgeSequence;
import com.tinkerpop.blueprints.pgm.util.wrappers.readonly.util.ReadOnlyVertexSequence;

/**
 * A ReadOnlyGraph wraps a Graph and overrides the underlying graph's mutating methods.
 * In this way, a ReadOnlyGraph can only be read from, not written to.
 *
 * @author Marko A. Rodriguez (http://markorodriguez.com)
 */
public class ReadOnlyGraph<T extends Graph> implements Graph, WrappableGraph<T> {

    protected final T rawGraph;

    public ReadOnlyGraph(final T rawGraph) {
        this.rawGraph = rawGraph;
    }

    /**
     * @throws UnsupportedOperationException
     */
    public void removeVertex(final Vertex vertex) throws UnsupportedOperationException {
        throw new UnsupportedOperationException(ReadOnlyTokens.MUTATE_ERROR_MESSAGE);
    }

    /**
     * @throws UnsupportedOperationException
     */
    public Edge addEdge(final Object id, final Vertex outVertex, final Vertex inVertex, final String label) throws UnsupportedOperationException {
        throw new UnsupportedOperationException(ReadOnlyTokens.MUTATE_ERROR_MESSAGE);
    }

    public Vertex getVertex(final Object id) {
        final Vertex vertex = this.rawGraph.getVertex(id);
        if (null == vertex)
            return null;
        else
            return new ReadOnlyVertex(vertex);
    }

    /**
     * @throws UnsupportedOperationException
     */
    public void removeEdge(final Edge edge) throws UnsupportedOperationException {
        throw new UnsupportedOperationException(ReadOnlyTokens.MUTATE_ERROR_MESSAGE);
    }

    public Iterable<Edge> getEdges() {
        return new ReadOnlyEdgeSequence(this.rawGraph.getEdges().iterator());
    }

    public Edge getEdge(final Object id) {
        final Edge edge = this.rawGraph.getEdge(id);
        if (null == edge)
            return null;
        else
            return new ReadOnlyEdge(edge);
    }

    public Iterable<Vertex> getVertices() {
        return new ReadOnlyVertexSequence(this.rawGraph.getVertices().iterator());
    }

    /**
     * @throws UnsupportedOperationException
     */
    public Vertex addVertex(final Object id) throws UnsupportedOperationException {
        throw new UnsupportedOperationException(ReadOnlyTokens.MUTATE_ERROR_MESSAGE);
    }

    /**
     * @throws UnsupportedOperationException
     */
    public void clear() throws UnsupportedOperationException {
        throw new UnsupportedOperationException(ReadOnlyTokens.MUTATE_ERROR_MESSAGE);
    }

    /**
     * @throws UnsupportedOperationException
     */
    public void shutdown() throws UnsupportedOperationException {
        throw new UnsupportedOperationException(ReadOnlyTokens.MUTATE_ERROR_MESSAGE);
    }

    public String toString() {
        return StringFactory.graphString(this, this.rawGraph.toString());
    }

    @Override
    public T getRawGraph() {
        return this.rawGraph;
    }

}
