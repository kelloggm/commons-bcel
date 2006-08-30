package org.apache.bcel.generic;

import java.io.DataOutputStream;
import java.io.IOException;
import org.apache.bcel.classfile.AnnotationElementValue;
import org.apache.bcel.classfile.ElementValue;

public class AnnotationElementValueGen extends ElementValueGen
{
	// For annotation element values, this is the annotation
	private AnnotationEntryGen a;

	public AnnotationElementValueGen(AnnotationEntryGen a, ConstantPoolGen cpool)
	{
		super(ANNOTATION, cpool);
		this.a = a;
	}

	public AnnotationElementValueGen(int type, AnnotationEntryGen annotation,
			ConstantPoolGen cpool)
	{
		super(type, cpool);
		if (type != ANNOTATION)
			throw new RuntimeException(
					"Only element values of type annotation can be built with this ctor");
		this.a = annotation;
	}

	public AnnotationElementValueGen(AnnotationElementValue value,
			ConstantPoolGen cpool, boolean copyPoolEntries)
	{
		super(ANNOTATION, cpool);
		a = new AnnotationEntryGen(value.getAnnotationEntry(), cpool, copyPoolEntries);
	}

	public void dump(DataOutputStream dos) throws IOException
	{
		dos.writeByte(type); // u1 type of value (ANNOTATION == '@')
		a.dump(dos);
	}

	public String stringifyValue()
	{
		throw new RuntimeException("Not implemented yet");
	}

	/**
	 * Return immutable variant of this AnnotationElementValueGen
	 */
	public ElementValue getElementValue()
	{
		return new AnnotationElementValue(this.type, a.getAnnotation(), cpGen
				.getConstantPool());
	}

	public AnnotationEntryGen getAnnotation()
	{
		return a;
	}
}